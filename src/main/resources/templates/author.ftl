<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Author Information</title>
    <style>
        /* Add your CSS styling here */
.author-container {
margin: 10px;
padding: 10px;
border: 1px solid #ccc;
}
.author-details {
margin-bottom: 10px;
}
</style>
</head>
<body>
<h1>Author Information</h1>


    <div class="author-list">
        <#list authorData as author>
            <div class="author-container">
                <div class="author-details">
                    <strong>Key:</strong> ${author.Key}
                </div>
                <div class="author-details">
                    <strong>Name:</strong> ${author.Name}
                </div>
                <div class="author-details">
                    <strong>Type:</strong> ${author.Type}
                </div>
                <div class="author-details">
                    <strong>Alternate Names:</strong>
                    <#if author.AlternateNames??>
                        <#list author.AlternateNames as name>
                            ${name}<#if name_has_next>, </#if>
                        </#list>
                    <#else>
                        None
                    </#if>
                </div>
                <div class="author-details">
                    <strong>Birth Date:</strong> ${author.BirthDate!"Not available"}
                </div>
                <div class="author-details">
                    <strong>Top Work:</strong> ${author.TopWork!"Not available"}
                </div>
                <div class="author-details">
                    <strong>Work Count:</strong> ${author.WorkCount}
                </div>
                <div class="author-details">
                    <strong>Top Subjects:</strong>
                    <#if author.TopSubjects??>
                        <#list author.TopSubjects as subject>
                            ${subject}<#if subject_has_next>, </#if>
                        </#list>
                    <#else>
                        None
                    </#if>
                </div>
                <div class="author-details">
                    <strong>Version:</strong> ${author._version_}
                </div>
            </div>
        </#list>
    </div>
</body>
</html>
