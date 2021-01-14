# ServerProject
To run this program, first update your own SQL username and password in src/resources/application.properties.

The website will be accessible on localhost:1337.

There are four different sets of login details available:
<table>
    <thead>
        <th>username</th>
        <th>password</th>
        <th>roles</th>
    </thead>
    <tbody>
        <tr>
            <td>Andy</td>
            <td>andy</td>
            <td>VIEWER</td>
        </tr>
        <tr>
            <td>Alexis</td>
            <td>alexis</td>
            <td>MAKER</td>
        </tr>
        <tr>
            <td>Dawn</td>
            <td>dawn</td>
            <td>MAKER, ADMIN</td>
        </tr>
        <tr>
            <td>Alan</td>
            <td>alan</td>
            <td>ADMIN</td>
        </tr>
    </tbody>
</table>

VIEWER can view all inventory items but not make any changes.

MAKER can view all items, amend existing items, create new items, and delete items.

ADMIN can view all items, review deleted items and either restore them or remove them from the database.
