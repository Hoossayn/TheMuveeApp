package com.example.data.mapper

import com.example.data.model.Movie
import com.example.data.model.MovieEntity
import com.example.test_util.RESPONSE_JSON_PATH
import com.example.test_util.utils.convertFromJsonToListOf
import com.example.test_util.utils.getResourceAsText
import com.google.common.truth.Truth
import org.junit.jupiter.api.Test

class DTOtoEntityMapperTest {

    private val postDTOList by lazy {
        convertFromJsonToListOf<Movie>(getResourceAsText(RESPONSE_JSON_PATH))!!
    }

    private val postEntityList by lazy {
        convertFromJsonToListOf<MovieEntity>(getResourceAsText(RESPONSE_JSON_PATH))!!
    }

    @Test
    fun `given PostDTO is input, should return PostEntity`() {

        val mapper = DTOtoEntityMapper()

        // GIVEN
        val expected = postEntityList

        // WHEN
        val actual = mapper.map(postDTOList)

        // THEN
        Truth.assertThat(actual).containsExactlyElementsIn(expected)
    }
}
