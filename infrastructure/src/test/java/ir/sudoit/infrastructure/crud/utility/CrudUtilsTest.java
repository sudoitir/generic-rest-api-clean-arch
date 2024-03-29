package ir.sudoit.infrastructure.crud.utility;

import lombok.Builder;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class CrudUtilsTest
{

    @Test
    void copyNonNullProperties()
    {
        String[] ignoreProperties = new String[]{"id", "version", "createdAt", "updatedAt"};

        Model source = Model.builder()
                .id(1)
                .version(0)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .text("text")
                .build();
        Model target = Model.builder().build();

        Model result = CrudUtils.copyNonNullProperties(source, target, ignoreProperties);

        System.out.println(result);

        assertThat(result).isEqualTo(target);
        assertThat(result.getId()).isNull();
        assertThat(result.getVersion()).isNull();
        assertThat(result.getCreatedAt()).isNull();
        assertThat(result.getUpdatedAt()).isNull();
        assertThat(result.getText()).isEqualTo("text");

        Model2 source2 = Model2.builder()
                .text("updated")
                .build();

        Model2 target2 = Model2.builder()
                .id(1)
                .version(0)
                .text("text")
                .build();

        Model2 result2 = CrudUtils.copyNonNullProperties(source2, target2, ignoreProperties);

        System.out.println(result2);

        assertThat(result2).isEqualTo(target2);
        assertThat(result2.getId()).isEqualTo(1);
        assertThat(result2.getVersion()).isEqualTo(0);
        assertThat(result2.getText()).isEqualTo("updated");
    }

    @Test
    void firstWordOf()
    {
        assertThat(CrudUtils.firstWordOf("SplitCamelCase"))
                .isEqualTo("split");

        assertThat(CrudUtils.firstWordOf("10SplitCamelCase"))
                .isEqualTo("10");

        assertThat(CrudUtils.firstWordOf("splitCamelCase"))
                .isEqualTo("split");
    }

    @Test
    void toSnakeCase()
    {
        assertThat(CrudUtils.toSnakeCase("SplitCamelCase"))
                .isEqualTo("split_camel_case");

        assertThat(CrudUtils.toSnakeCase("10SplitCamelCase"))
                .isEqualTo("10_split_camel_case");

        assertThat(CrudUtils.toSnakeCase("splitCamelCase"))
                .isEqualTo("split_camel_case");
    }

    @Test
    void splitCamelCase()
    {
        assertThat(CrudUtils.splitCamelCase("SplitCamelCase"))
                .containsExactly("Split", "Camel", "Case");

        assertThat(CrudUtils.splitCamelCase("10SplitCamelCase"))
                .containsExactly("10", "Split", "Camel", "Case");

        assertThat(CrudUtils.splitCamelCase("Split10CamelCase"))
                .containsExactly("Split10", "Camel", "Case");

        assertThat(CrudUtils.splitCamelCase("SplitCamelCase10"))
                .containsExactly("Split", "Camel", "Case10");
    }

    @Data
    @Builder
    private static class Model
    {
        private Integer id;
        private Integer version;
        private Instant createdAt;
        private Instant updatedAt;
        private String text;
    }

    @Data
    @Builder
    private static class Model2
    {
        private Integer id;
        private Integer version;
        private String text;
    }
}