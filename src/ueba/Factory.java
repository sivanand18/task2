package ueba;

public class Factory {
    public Object getanyconnection(String val)
    {
        if(val=="dbconnection")
        {
            return new Dbconnection();
        }
        if(val=="propertyconnection")
        {
            return  new Propertyconnection();
        }
        return null;
    }
}
