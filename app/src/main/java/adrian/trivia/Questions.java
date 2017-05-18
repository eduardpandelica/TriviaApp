package adrian.trivia;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Questions implements Parcelable{

@SerializedName("response_code")
@Expose
private Integer responseCode;
@SerializedName("results")
@Expose
private List<Result> results = null;

    protected Questions(Parcel in) {
        results = in.createTypedArrayList(Result.CREATOR);
        responseCode = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(results);
        dest.writeInt(responseCode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Questions> CREATOR = new Creator<Questions>() {
        @Override
        public Questions createFromParcel(Parcel in) {
            return new Questions(in);
        }

        @Override
        public Questions[] newArray(int size) {
            return new Questions[size];
        }
    };

    public Integer getResponseCode() {
return responseCode;
}

public void setResponseCode(Integer responseCode) {
this.responseCode = responseCode;
}

public List<Result> getResults() {
return results;
}

public void setResults(List<Result> results) {
this.results = results;
}

}