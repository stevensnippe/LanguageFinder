package hadoop.model;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class CharacterPair implements Writable, WritableComparable<CharacterPair> {

	/**
	 * The base character from which we're looking for neighbors
	 */
    private Text character;
    
    /**
     * A neighbor character which is next to {@link #character}
     */
    private Text neighbor;

    public CharacterPair(Text character, Text neighbor) {
        this.character = character;
        this.neighbor = neighbor;
    }

    public CharacterPair(char character, char neighbor) {
        this(new Text(String.valueOf(character)), new Text(String.valueOf(neighbor)));
    }

    public CharacterPair() {
        this.character = new Text();
        this.neighbor = new Text();
    }
    
    public void setSource(char character){
        this.character.set(String.valueOf(character));
    }
    
    public void setNeighbor(char neighbor){
        this.neighbor.set(String.valueOf(neighbor));
    }

    @Override
    public void write(DataOutput output) throws IOException {
        character.write(output);
        neighbor.write(output);
    }

    @Override
    public void readFields(DataInput input) throws IOException {
        character.readFields(input);
        neighbor.readFields(input);
    }

    @Override
    public String toString() {
        return character.toString() + neighbor.toString();
    }

	@Override
	public int compareTo(CharacterPair other) {
        int result = character.compareTo(other.character);
        return result != 0 ? result : neighbor.compareTo(other.neighbor);
	}
}