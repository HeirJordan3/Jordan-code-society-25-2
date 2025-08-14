import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class JohnnySmithQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'johnnysmith';
  }

  makeQuizQuestions(): QuizQuestion[] {
    return [
      JohnnySmithQuiz.makeQuestion0(), 
      JohnnySmithQuiz.makeQuestion1(),
      JohnnySmithQuiz.makeQuestion2()
    ];
  }

  private static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      'Which data structure follows the Last-In-First-Out (LIFO) principle?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Queue'],
        [AnswerChoice.B, 'Array'],
        [AnswerChoice.C, 'Stack'],
        [AnswerChoice.D, 'Linked List'],
      ]),
      AnswerChoice.UNANSWERED,
    );
  }

  private static makeQuestion1(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      1,
      'What does the acronym "API" stand for in software development?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Advanced Programming Interface'],
        [AnswerChoice.B, 'Application Programming Interface'],
        [AnswerChoice.C, 'Automated Program Integration'],
        [AnswerChoice.D, 'Application Process Integration'],
      ]),
      AnswerChoice.UNANSWERED,
    );
  }

  private static makeQuestion2(): QuizQuestion {
    return new QuizQuestion(
      2,
      'What is the time complexity of searching for an element in a balanced binary search tree?',
      'O(log n)',
    );
  }
}