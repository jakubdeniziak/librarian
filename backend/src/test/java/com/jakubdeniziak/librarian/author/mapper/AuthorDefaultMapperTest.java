package com.jakubdeniziak.librarian.author.mapper;

import com.jakubdeniziak.librarian.author.domain.Author;
import com.jakubdeniziak.librarian.author.dto.AuthorRequest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class AuthorDefaultMapperTest {

    @InjectMocks
    private final AuthorDefaultMapper authorMapper = new AuthorDefaultMapper();

    @ParameterizedTest
    @MethodSource("authorRequests")
    public void shouldReturnAuthorForRequest(UUID authorId, AuthorRequest authorRequest) {
        Author result = authorMapper.map(authorId, authorRequest);

        assertThat(result)
                .returns(authorId, Author::getId)
                .returns(authorRequest.getFirstName(), Author::getFirstName)
                .returns(authorRequest.getLastName(), Author::getLastName)
                .returns(authorRequest.getDescription(), Author::getDescription);
    }

    private static Stream<Arguments> authorRequests() {
        return Stream.of(
                Arguments.of(
                        UUID.fromString("81221af1-b05a-432c-ae36-0f927151229a"),
                        AuthorRequest.builder()
                                .firstName("John")
                                .lastName("Doe")
                                .description("Great author")
                                .build()
                ),
                Arguments.of(
                        UUID.fromString("f8f520ca-843c-49dd-a085-22dfeaaa1b06"),
                        AuthorRequest.builder()
                                .firstName("Robert")
                                .build()
                ),
                Arguments.of(
                        UUID.fromString("b9b79018-fa4a-4a52-a314-3f4e100c5496"),
                        AuthorRequest.builder()
                                .lastName("Smith")
                                .build()
                ),
                Arguments.of(
                        UUID.fromString("ed3e3cba-4111-4dd8-8a03-d1bf76fe8ab2"),
                        AuthorRequest.builder()
                                .description("Best selling author")
                                .build()
                ),
                Arguments.of(
                        UUID.fromString("1f293569-df2c-4691-b76f-6f7501c2dea2"),
                        AuthorRequest.builder()
                                .build()
                )
        );
    }

}
