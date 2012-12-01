<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form method="post" action="<c:url value="inscription/new"/>">
    <table  border="0">	   
        <tr>
            <td> <label for="for1"> nom </label> </td>
            <td> <input id="for1" name="nom" type="text" /> </td>
        </tr>

        <tr>
            <td> <label for="for2"> prenom </label> </td>
            <td> <input id="for1" name="prenom" type="text" /> </td>
        </tr>
        
        <tr>
            <td> <label for="for2"> taille en cm </label> </td>
            <td> <input id="for1" name="taille" type="text" /> </td>
        </tr>
        <tr>
            <td> <label for="for2"> tok </label> </td>
            <td>  <input id="for1" name="nom" type="submit" /> </td>
        </tr>

    </table>
</form>