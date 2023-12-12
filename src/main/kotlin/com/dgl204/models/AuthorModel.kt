package com.dgl204.models

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class AuthorResponse(
    val numFound: Int,
    val start: Int,
    val numFoundExact: Boolean,
    val docs: List<Author>
)

@Serializable
data class Author(
    val key: String,
    val type: String,
    val death_date: String? = null,
    val name: String,
    val alternate_names: List<String>? = null,
    val birth_date: String? = null,
    val top_work: String? = null,
    val work_count: Int,
    val top_subjects: List<String>? = null,
    val _version_: Long // Changed to Long
)


object LongAsStringSerializer : KSerializer<Long> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("_version_", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Long) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): Long {
        return decoder.decodeString().toLong()
    }
}

fun createAuthorDataModel(authorResponse: AuthorResponse): List<Map<String, Any?>> {
    return authorResponse.docs.map { author ->
        mapOf(
            "Key" to author.key,
            "Type" to author.type,
            "Name" to author.name,
            "AlternateNames" to author.alternate_names,
            "BirthDate" to author.birth_date,
            "TopWork" to author.top_work,
            "WorkCount" to author.work_count,
            "TopSubjects" to author.top_subjects,
            "_version_" to author._version_
        )
    }
}

