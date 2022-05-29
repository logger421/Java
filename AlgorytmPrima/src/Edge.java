public class Edge implements Comparable<Edge>{
    public int start;
    public int end;
    public int weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return weight == edge.weight;
    }

    @Override
    public int compareTo(Edge e) {
        if (weight < e.weight)
            return -1;
        else if (weight > e.weight)
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        return " (x->y: " + start + "->" + end + ") w: " + weight + " ";
    }
    //    @Override
//    public int hashCode() {
//        return Objects.hash(weight);
//    }

}
