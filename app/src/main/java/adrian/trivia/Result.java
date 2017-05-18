package adrian.trivia;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Parcelable{

@SerializedName("category")
@Expose
private String category;
@SerializedName("type")
@Expose
private String type;
@SerializedName("difficulty")
@Expose
private String difficulty;
@SerializedName("question")
@Expose
private String question;
@SerializedName("correct_answer")
@Expose
private String correctAnswer;
@SerializedName("incorrect_answers")
@Expose
private List<String> incorrectAnswers = null;

    protected Result(Parcel in) {
        category = in.readString();
        type = in.readString();
        difficulty = in.readString();
        question = in.readString();
        correctAnswer = in.readString();
        incorrectAnswers = in.createStringArrayList();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public String getCategory() {
return category;
}

public void setCategory(String category) {
this.category = category;
}

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public String getDifficulty() {
return difficulty;
}

public void setDifficulty(String difficulty) {
this.difficulty = difficulty;
}

public String getQuestion() {
return question;
}

public void setQuestion(String question) {
this.question = question;
}

public String getCorrectAnswer() {
return correctAnswer;
}

public void setCorrectAnswer(String correctAnswer) {
this.correctAnswer = correctAnswer;
}

public List<String> getIncorrectAnswers() {
return incorrectAnswers;
}

public void setIncorrectAnswers(List<String> incorrectAnswers) {
this.incorrectAnswers = incorrectAnswers;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(category);
        dest.writeString(type);
        dest.writeString(difficulty);
        dest.writeString(question);
        dest.writeString(correctAnswer);
        dest.writeStringList(incorrectAnswers);
    }
}