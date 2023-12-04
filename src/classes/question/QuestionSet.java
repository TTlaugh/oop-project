package classes.question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import classes.repository.QuestionRepository;
import classes.subject.Subject;
import classes.util.Constant;

public class QuestionSet {

	private Subject subject;
	private int quesCount;
	private ArrayList<Question> quesSet;
	private ArrayList<QuestionCountDetail> questionCountDetail;
	private QuestionRepository quesRepo;

	public QuestionSet(Subject subject, int quesCount, ArrayList<QuestionCountDetail> questionCountDetail) {
		this.subject = subject;
		this.quesCount = quesCount;
		this.questionCountDetail = questionCountDetail;
		this.quesRepo = new QuestionRepository(Constant.dataPath.QuestionBanks_Dir + subject.getId());
		this.quesSet = new ArrayList<Question>();
		if (!createQuestionSet()) {
			quesSet = null;
		}
	}

	private boolean createQuestionSet() {
		for (QuestionCountDetail detail : questionCountDetail) {
			ArrayList<Question> questionsOfChapter = quesRepo.searchQuestionByChapter(detail.getChapter());
			if (questionsOfChapter.size() == 0)
				return false;
			for (int i = 0; i < detail.getDifficultyCountDetail().size(); i++) {
				ArrayList<Question> questionsOfDifficulty = QuestionRepository.searchQuestionByDiffi(questionsOfChapter,
						i);
				int quesCountOfDiffi = detail.getDifficultyCountDetail().get(i);
//				if (questionsOfDifficulty.size() == 0 || questionsOfDifficulty.size() < quesCountOfDiffi)
//					return false;
				Random randNum = new Random();
				Set<Integer> set = new LinkedHashSet<Integer>();
				while (set.size() < quesCountOfDiffi) {
					int next = randNum.nextInt(questionsOfDifficulty.size());
					if (set.add(next))
						quesSet.add(questionsOfDifficulty.get(next));
				}
			}
		}
		return true;
	}

	public void shuffleQuestionSet() {
		Collections.shuffle(this.quesSet);
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public int getQuesCount() {
		return quesCount;
	}

	public void setQuesCount(int quesCount) {
		this.quesCount = quesCount;
	}

	public ArrayList<Question> getQuesSet() {
		return quesSet;
	}

	public void setQuesSet(ArrayList<Question> quesSet) {
		this.quesSet = quesSet;
	}

	public ArrayList<QuestionCountDetail> getQuestionCountDetail() {
		return questionCountDetail;
	}

	public void setQuestionCountDetail(ArrayList<QuestionCountDetail> questionCountDetail) {
		this.questionCountDetail = questionCountDetail;
	}

	public QuestionRepository getQuesbank() {
		return quesRepo;
	}

	public void setQuesbank(QuestionRepository quesbank) {
		this.quesRepo = quesbank;
	}

}
