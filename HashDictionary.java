import java.util.LinkedList;

public class HashDictionary implements DictionaryADT {
    private LinkedList<Data>[] hashTable;
    private int size;

    public HashDictionary(int size) {
        this.size = size;
        hashTable = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            hashTable[i] = new LinkedList<>();
        }
    }

    @Override
    public int put(Data record) throws DictionaryException {
        int index = hash(record.getConfiguration());
        LinkedList<Data> entries = hashTable[index];
        for (Data entry : entries) {
            if (entry.getConfiguration().equals(record.getConfiguration())) {
                throw new DictionaryException();
            }
        }
        entries.add(record);
        return entries.size() > 1 ? 1 : 0;
    }

    @Override
    public void remove(String config) throws DictionaryException {
        int index = hash(config);
        LinkedList<Data> entries = hashTable[index];
        boolean found = false;
        for (Data entry : entries) {
            if (entry.getConfiguration().equals(config)) {
                entries.remove(entry);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new DictionaryException();
        }
    }

    @Override
    public int get(String config) {
        int index = hash(config);
        for (Data entry : hashTable[index]) {
            if (entry.getConfiguration().equals(config)) {
                return entry.getScore();
            }
        }
        return -1;
    }

    @Override
    public int numRecords() {
        int count = 0;
        for (LinkedList<Data> bucket : hashTable) {
            count += bucket.size();
        }
        return count;
    }

    private int hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (55 * hash + key.charAt(i)) % size;
        }
        return hash;
    }
}
