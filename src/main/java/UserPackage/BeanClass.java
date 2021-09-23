package UserPackage;

public class BeanClass {
    int empId,categoryId,questionId,rating;
    float comments;
    BeanClass(){}

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public float getComments() {
        return comments;
    }

    public void setComments(float comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "BeanClass{" +
                "empId=" + empId +
                ", categoryId=" + categoryId +
                ", questionId=" + questionId +
                ", rating=" + rating +
                ", comments=" + comments +
                '}';
    }
}
