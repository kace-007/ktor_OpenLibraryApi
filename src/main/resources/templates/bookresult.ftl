<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book Details</title>
</head>
<body>
    <h1>${bookData["Title"]!"Unknown Title"}</h1>

    <p><strong>Publishers:</strong>
    <#if bookData["Publishers"]?is_sequence>
        ${bookData["Publishers"]?join(", ")}
    <#else>
        ${bookData["Publishers"]!"null"}
    </#if>
</p>

    <p><strong>Number of Pages:</strong> ${bookData["NumberOfPages"]!"0"}</p>
    <p><strong>Subtitle:</strong> ${bookData["Subtitle"]!"null"}</p>

    <p><strong>ISBN-10:</strong>
    <#if bookData["ISBN10"]?is_sequence>${bookData["ISBN10"]?join(", ")}
    <#else>${bookData["ISBN10"]!"null"}</#if>
</p>

    <p><strong>LC Classifications:</strong>
    <#if bookData["LcClassifications"]?is_sequence>${bookData["LcClassifications"]?join(", ")}
    <#else>${bookData["LcClassifications"]!"null"}</#if>
</p>

    <p><strong>Latest Revision:</strong> ${bookData["LatestRevision"]!"0"}</p>
    <p><strong>Key:</strong> ${bookData["OpenLibrary Key"]!"null"}</p>

<p><strong>Authors:</strong>
    <#if bookData["Authors"]?is_sequence>${bookData["Authors"]?join(", ")}
    <#else>${bookData["Authors"]!"null"}</#if>
</p>

    <p><strong>OCAID:</strong> ${bookData["Ocaid"]!"null"}</p>

    <p><strong>Publish Places:</strong>
    <#if bookData["PublishPlaces"]?is_sequence>${bookData["PublishPlaces"]?join(", ")}
    <#else>${bookData["PublishPlaces"]!"null"}</#if>
</p>


<p><strong>Contributions:</strong>
    <#if bookData["Contributions"]?is_sequence>${bookData["Contributions"]?join(", ")}
    <#else>${bookData["Contributions"]!"null"}</#if>
</p>

    <p><strong>Subjects:</strong>
    <#if bookData["Subjects"]?is_sequence>
        ${bookData["Subjects"]?join(", ")}
    <#else>
        ${bookData["Subjects"]!"null"}
    </#if>
</p>

    <p><strong>Languages:</strong>
    <#if bookData["Languages"]?is_sequence>
        ${bookData["Languages"]?join(", ")}
    <#else>
        ${bookData["Languages"]!"null"}
    </#if>
</p>


    <p><strong>Subjects:</strong>
    <#if bookData["Subjects"]?is_sequence>${bookData["Subjects"]?join(", ")}
    <#else>${bookData["Subjects"]!"null"}</#if>
</p>

    <p><strong>Source Records:</strong>
    <#if bookData["SourceRecords"]?is_sequence>
        ${bookData["SourceRecords"]?join(", ")}
    <#else>
        ${bookData["SourceRecords"]!"null"}
    </#if>
</p>


    <p><strong>Dewey Decimal Class:</strong>
    <#if bookData["DeweyDecimalClass"]?is_sequence>
        ${bookData["DeweyDecimalClass"]?join(", ")}
    <#else>
        ${bookData["DeweyDecimalClass"]!"null"}
    </#if>
</p>


    <p><strong>Notes:</strong> ${bookData["Notes"]!"null"}</p>

    <p><strong>Edition Name:</strong> ${bookData["EditionName"]!"null"}</p>

    <p><strong>LCCN:</strong>
    <#if bookData["Lccn"]?is_sequence>
        ${bookData["Lccn"]?join(", ")}
    <#else>
        ${bookData["Lccn"]!"null"}
    </#if>
</p>

    <p><strong>Local ID:</strong>
    <#if bookData["LocalId"]?is_sequence>
        ${bookData["LocalId"]?join(", ")}
    <#else>
        ${bookData["LocalId"]!"null"}
    </#if>
</p>

    <p><strong>Publish Date:</strong> ${bookData["PublishDate"]!"null"}</p>
    <p><strong>Publish Country:</strong> ${bookData["PublishCountry"]!"null"}</p>

    <p><strong>By Statement:</strong> ${bookData["ByStatement"]!"null"}</p>

    <p><strong>Revision:</strong> ${bookData["Revision"]!"0"}</p>
</body>
</html>
