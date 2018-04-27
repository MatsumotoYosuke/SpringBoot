package form;

import javax.validation.constraints.NotEmpty;

public class LoginForm {
	
	// エラーメッセージ共通化は後程実装予定
	
    @NotEmpty(message = "ユーザーIDに値を入力してください")
    private String id;
    @NotEmpty(message = "ユーザーPASSに値を入力してください")
    //@Pattern(regexp = "[0-9]*", message="数字で入力してください")
    private String pass;
    private boolean flag;
    public String getId() {
        return id;
    }

    public void setId(String Id) {
        this.id = Id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String Pass) {
        this.pass = Pass;
    }
    public boolean getflag() {
        return flag;
    }

    public void setflag(boolean Flag) {
        this.flag = Flag;
    }

}
