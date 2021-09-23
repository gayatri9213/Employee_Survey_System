package UserPackage;
/*
* @Auhthor : Mayur Pardeshi
* */
public class Responses {
    private int fetchedQuestionId;
    private String fetchedQuestion;
    private float rating;

    Responses(){}

    public Responses(int fetchedQuestionId, String fetchedQuestion, float rating){
        this.fetchedQuestionId=fetchedQuestionId;
        this.fetchedQuestion=fetchedQuestion;
        this.rating=rating;
    }

    public int getFetchedQuestionId() {
        return fetchedQuestionId;
    }

    public void setFetchedQuestionId(int fetchedQuestionId) {
        this.fetchedQuestionId = fetchedQuestionId;
    }

    public String getFetchedQuestion() {
        return fetchedQuestion;
    }

    public void setFetchedQuestion(String fetchedQuestion) {
        this.fetchedQuestion = fetchedQuestion;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Responses{" +
                "fetchedQuestionId=" + fetchedQuestionId +
                ", fetchedQuestion='" + fetchedQuestion + '\'' +
                ", rating=" + rating +
                '}';
    }
}
