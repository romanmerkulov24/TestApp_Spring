package ua.nure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import ua.nure.dto.ResponseDto;
import ua.nure.entity.Field;
import ua.nure.entity.Response;
import ua.nure.repository.FieldRepo;
import ua.nure.repository.ResponseRepo;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

@Service
public class ResponseService {

    private static final String N_A = "N/A";
    private static final String COMMA = ",";

    @Autowired
    private ResponseRepo responseRepo;

    @Autowired
    private FieldRepo fieldRepo;

    @Transactional
    public List<List<String>> getResponses() {
        List<Field> dbFields = fieldRepo.findAllByActive(Boolean.TRUE);
        return responseRepo.findAllByFieldActive(Boolean.TRUE).stream()

                //Map<UUID, Response> - map to distinct rows
                .collect(Collectors.groupingBy(Response::getResponseUuid))
                .values().stream()
                .map(row -> row.stream()

                        //Map<Field, Collection<Response>> - separate by fields
                        .collect(Collectors.groupingBy(Response::getField))
                        .entrySet().stream()
                        .collect(Collectors.toMap(Entry::getKey, entry -> flattenResponses(entry.getValue()))))

                //map to active fields
                .map(row -> getOrderedReponseRow(row, dbFields))
                .collect(Collectors.toList());
    }

    public void saveResponse(List<ResponseDto> dtos) {
        UUID responseUuid = UUID.randomUUID();
        List<Response> responses = dtos.stream()
                .map(responseDto -> new Response(responseDto.getInitialField(), responseUuid.toString(),
                        responseDto.getFieldValue()))
                .collect(Collectors.toList());
        responseRepo.saveAll(responses);
    }

    private String flattenResponses(Collection<Response> responses) {
        if (CollectionUtils.isEmpty(responses)) {
            return N_A;
        }
        return responses.stream()
                .map(Response::getValue)
                .collect(joining(COMMA));
    }

    private List<String> getOrderedReponseRow(Map<Field, String> dbResponses, List<Field> dbFields) {
        return dbFields.stream().map(field -> dbResponses.getOrDefault(field, N_A))

                //to avoid empty strings
                .map(field -> StringUtils.isEmpty(field) ? N_A : field)
                .collect(Collectors.toList());
    }
}
