package org.taoran.course.pojo;

/**
 * ÎÊ´ðÖªÊ¶model
 * 
 * @author ßØßØ
 * @date 2015-4-24
 */
public class Knowledge {
	private int id;
	private String question;
	private String answer;
	private int category;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
}
