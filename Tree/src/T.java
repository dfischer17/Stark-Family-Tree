
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T {

    private String name;
    private GENDER gender;

    static Map<T, List<T>> children = new HashMap<>();
    static Map<T, List<T>> parents = new HashMap<>();
    static T rickard = new T("Rickard Stark", GENDER.MALE);
    static T lyarra = new T("Lyarra Stark", GENDER.FEMALE);

    static T catelyn = new T("Catelyn Stark", GENDER.FEMALE); // Ausnahme!
    static T eddard = new T("Rickard Stark", GENDER.MALE);
    static T brandon = new T("Brandon Stark", GENDER.MALE);
    static T benjen = new T("Benjen Stark", GENDER.MALE);
    static T lyanna = new T("Lyanna Stark", GENDER.FEMALE);

    static T robb = new T("Robb Stark", GENDER.MALE);
    static T sansa = new T("Sansa Stark", GENDER.FEMALE);
    static T arya = new T("Arya Stark", GENDER.FEMALE);
    static T bran = new T("Bran Stark", GENDER.MALE);
    static T rickon = new T("Rickon Stark", GENDER.MALE);

    public T() {

    }

    public T(String name, GENDER gender) {
        this.name = name;
        this.gender = gender;
    }

    public static void setup() {
        // Personen
        rickard = new T("Rickard Stark", GENDER.MALE);
        lyarra = new T("Lyarra Stark", GENDER.FEMALE);

        catelyn = new T("Catelyn Stark", GENDER.FEMALE); // Ausnahme!
        eddard = new T("Richard Stark", GENDER.MALE);
        brandon = new T("Brandon Stark", GENDER.MALE);
        benjen = new T("Benjen Stark", GENDER.MALE);
        lyanna = new T("Lyanna Stark", GENDER.FEMALE);

        robb = new T("Robb Stark", GENDER.MALE);
        sansa = new T("Sansa Stark", GENDER.FEMALE);
        arya = new T("Arya Stark", GENDER.FEMALE);
        bran = new T("Bran Stark", GENDER.MALE);
        rickon = new T("Rickon Stark", GENDER.MALE);

        // Eltern der Personen mittlerer Reihe       
        parents.put(eddard, new ArrayList<>(Arrays.asList(rickard, lyarra)));
        parents.put(brandon, Arrays.asList(rickard, lyarra));
        parents.put(benjen, Arrays.asList(rickard, lyarra));
        parents.put(lyanna, Arrays.asList(rickard, lyarra));

        // Eltern der Personen unterer Reihe
        parents.put(robb, Arrays.asList(catelyn, eddard));
        parents.put(sansa, Arrays.asList(catelyn, eddard));
        parents.put(arya, Arrays.asList(catelyn, eddard));
        parents.put(bran, Arrays.asList(catelyn, eddard));
        parents.put(rickon, Arrays.asList(catelyn, eddard));

        // Kinder der Personen obere Reihe
        children.put(rickard, Arrays.asList(eddard, brandon, benjen, lyanna));
        children.put(lyarra, Arrays.asList(eddard, brandon, benjen, lyanna));

        // Kinder der Personen mittlerer Reihe
        children.put(catelyn, Arrays.asList(robb, sansa, arya, bran, rickon));
        children.put(eddard, Arrays.asList(robb, sansa, arya, bran, rickon));
    }

    public static boolean isParent(T parent, T child) {
        if (children.get(parent) != null) {
            for (T c : children.get(parent)) {
                if (child.equals(c)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isFemale(T person) {
        return person.gender.equals(GENDER.FEMALE);
    }

    public static boolean isGrandparent(T grandparent, T grandchild) {
        // Durchlaufe alle Kinder des grandparent
        if (children.get(grandparent) != null) {
            for (T parent : children.get(grandparent)) {
                // Durchlaufe alle Kinder der Kinder des grandparent
                if (children.get(parent) != null) {
                    for (T gchild : children.get(parent)) {
                        if (gchild.equals(grandchild)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static List<T> getAllGrandparents(T grandchild) {
        List<T> grandparents = new ArrayList<>();

        // Eltern des grandchild werden durchlaufen
        if (parents.get(grandchild) != null) {
            for (T parent : parents.get(grandchild)) {
                if (parents.get(parent) != null) {
                    for (T grandparent : parents.get(parent)) {
                        grandparents.add(grandparent);
                    }
                }
            }
        }
        return grandparents;
    }

    public static List<T> getAllGrandchildren(T grandparent) {
        List<T> grandchildren = new ArrayList<>();

        // Kinder des grandparent werden durchlaufen
        if (children.get(grandparent) != null) {
            for (T child : children.get(grandparent)) {
                if (children.get(child) != null) {
                    // Kinder der Kinder des grandparent werden durchlaufen
                    for (T gChildren : children.get(child)) {
                        grandchildren.add(gChildren);
                    }
                }
            }
        }
        return grandchildren;
    }

    public static Map<T, List<T>> getAllSiblings(T root) {
        List<T> siblings = new ArrayList<>();
        Map<T, List<T>> combined = new HashMap<>();

        if (children.get(parents.get(root)) != null) {
            for (T sibling : children.get(parents.get(root).get(0))) {
                // root wird nicht als Geschwister von root angezeigt
                if (!sibling.equals(root)) {
                    siblings.add(sibling);
                }
            }
        }

        // root und gefundene Geschwister zusammensetzen
        combined.put(root, siblings);
        return combined;
    }

    public static List<T> getAllGrandmas(T root) {
        List<T> grandmas = new ArrayList<>();

        if (parents.get(root) != null) {
            for (T parent : parents.get(root)) {
                if (parents.get(parent) != null) {
                    for (T grandparent : parents.get(parent)) {
                        if (grandparent.gender.name().equals("FEMALE")) {
                            grandmas.add(grandparent);
                        }
                    }
                }
            }
        }
        return grandmas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GENDER getGender() {
        return gender;
    }

    public void setGender(GENDER gender) {
        this.gender = gender;
    }

    public static Map<T, List<T>> getChildren() {
        return children;
    }

    public static void setChildren(Map<T, List<T>> children) {
        T.children = children;
    }

    public static Map<T, List<T>> getParents() {
        return parents;
    }

    public static void setParents(Map<T, List<T>> parents) {
        T.parents = parents;
    }

    @Override
    public String toString() {
        return name + " " + gender;
    }
}
