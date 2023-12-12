package com.dgl204.models

import kotlinx.serialization.Serializable

@Serializable
data class BookResponse(
    val publishers: List<String>? = null,
    val number_of_pages: Int? = null,
    val subtitle: String? = null,
    val isbn_10: List<String>? = null,
    val covers: List<Int>? = null,
    val lc_classifications: List<String>? = null,
    val latest_revision: Int? = null,
    val key: String? = null,
    val authors: List<AuthorRef>? = null,
    val ocaid: String? = null,
    val publish_places: List<String>? = null,
    val contributions: List<String>? = null,
    val subjects: List<String>? = null,
    val languages: List<LanguageRef>? = null,
    val pagination: String? = null,
    val source_records: List<String>? = null,
    val title: String? = null,
    val dewey_decimal_class: List<String>? = null,
    val notes: Notes? = null,
    val identifiers: Identifiers? = null,
    val created: Created? = null,
    val edition_name: String? = null,
    val lccn: List<String>? = null,
    val local_id: List<String>? = null,
    val publish_date: String? = null,
    val publish_country: String? = null,
    val last_modified: LastModified? = null,
    val by_statement: String? = null,
    val works: List<WorkRef>? = null,
    val type: Type? = null,
    val revision: Int? = null
)


@Serializable
data class AuthorRef(
    val key: String? = null
)

@Serializable
data class LanguageRef(
    val key: String? = null
)

@Serializable
data class Notes(
    val type: String? = null,
    val value: String? = null
)

@Serializable
data class Identifiers(
    val librarything: List<String>? = null,
    val wikidata: List<String>? = null,
    val goodreads: List<String>? = null,
    val classifications: List<String>? = null
)

@Serializable
data class Created(
    val type: String? = null,
    val value: String? = null
)

@Serializable
data class LastModified(
    val type: String? = null,
    val value: String? = null
)

@Serializable
data class WorkRef(
    val key: String? = null
)

@Serializable
data class Type(
    val key: String? = null
)


fun createBookDataModel(bookResponse: BookResponse): Map<String, Any?> {
    return mapOf(
        "Publishers" to (bookResponse.publishers ?: "null"),
        "NumberOfPages" to (bookResponse.number_of_pages ?: 0),
        "Subtitle" to (bookResponse.subtitle ?: "null"),
        "ISBN10" to (bookResponse.isbn_10 ?: "null"),
        "Covers" to (bookResponse.covers ?: "null"),
        "LcClassifications" to (bookResponse.lc_classifications ?: "null"),
        "LatestRevision" to (bookResponse.latest_revision ?: 0),
        "Key" to (bookResponse.key ?: "null"),
        "Authors" to (bookResponse.authors?.map { it.key } ?: "null"),
        "Ocaid" to (bookResponse.ocaid ?: "null"),
        "PublishPlaces" to (bookResponse.publish_places ?: "null"),
        "Contributions" to (bookResponse.contributions ?: "null"),
        "Subjects" to (bookResponse.subjects ?: "null"),
        "Languages" to (bookResponse.languages?.map { it.key } ?: "null"),
        "Pagination" to (bookResponse.pagination ?: "null"),
        "SourceRecords" to (bookResponse.source_records ?: "null"),
        "Title" to (bookResponse.title ?: "null"),
        "DeweyDecimalClass" to (bookResponse.dewey_decimal_class ?: "null"),
        "Notes" to (bookResponse.notes ?: "null"),
        "Identifiers" to (bookResponse.identifiers ?: "null"),
        "Created" to (bookResponse.created ?: "null"),
        "EditionName" to (bookResponse.edition_name ?: "null"),
        "Lccn" to (bookResponse.lccn ?: "null"),
        "LocalId" to (bookResponse.local_id ?: "null"),
        "PublishDate" to (bookResponse.publish_date ?: "null"),
        "PublishCountry" to (bookResponse.publish_country ?: "null"),
        "LastModified" to (bookResponse.last_modified ?: "null"),
        "ByStatement" to (bookResponse.by_statement ?: "null"),
        "Works" to (bookResponse.works ?: "null"),
        "Type" to (bookResponse.type ?: "null"),
        "Revision" to (bookResponse.revision ?: 0)
    )
}
