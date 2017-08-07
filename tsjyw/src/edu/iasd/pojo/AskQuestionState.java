package edu.iasd.pojo;
// default package

import java.util.HashSet;
import java.util.Set;


/**
 * AskQuestionState entity. @author MyEclipse Persistence Tools
 */

public class AskQuestionState  implements java.io.Serializable {

	 public static final int STATE_OPEN = 1;
	 public static final int STATE_CLOSED = 2;
	 public static final int STATE_DISABLED = 3;

    // Fields    

     private Integer askQuestionStateId;
     private String askQuestionStateName;


    // Constructors

    /** default constructor */
    public AskQuestionState() {
    }

	/** minimal constructor */
    public AskQuestionState(String askQuestionStateName) {
        this.askQuestionStateName = askQuestionStateName;
    }
    
	public Integer getAskQuestionStateId() {
		return askQuestionStateId;
	}

	public void setAskQuestionStateId(Integer askQuestionStateId) {
		this.askQuestionStateId = askQuestionStateId;
	}

	public String getAskQuestionStateName() {
		return askQuestionStateName;
	}

	public void setAskQuestionStateName(String askQuestionStateName) {
		this.askQuestionStateName = askQuestionStateName;
	}
}