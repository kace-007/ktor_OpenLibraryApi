<h1>Hello ${username}!</h1>
<h2>${message}</h2>

<h3>Search for cover</h3>
<form action="/submit-cover" method="post">
        <input type="text" name="cover" placeholder="Enter ISBN" />
        <input type="submit" value="Search Cover" />
    </form>

<h3>Search for Author</h3>
<form action="/submit-author" method="post">
    <input type="text" name="author" placeholder="Enter author name" />
    <input type="submit" value="Search Author" />
</form>

<h3>Search Books</h3>
<form action="/submit" method="post">
    <input type="text" name="title" placeholder="Enter ISBN" />
    <input type="submit" value="Search Book" />
</form>
