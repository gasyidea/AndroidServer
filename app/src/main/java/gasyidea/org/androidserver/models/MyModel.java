package gasyidea.org.androidserver.models;

public class MyModel {
    private String tel;

    public MyModel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "MyModel{" +
                "tel='" + tel + '\'' +
                '}';
    }
}
