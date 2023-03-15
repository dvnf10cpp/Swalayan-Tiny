package DataStructure;

public class Pair<Key, Value> 
{
    private final Key key;
    private Value value;
    
    public Pair(Key key, Value value)
    {
        this.key = key;
        this.value = value;
    }

    public Key getKey()
    {
        return this.key;
    }

    public Value getValue()
    {
        return this.value;
    }

    public void setValue(Value value)
    {
        this.value = value;
    }
}
