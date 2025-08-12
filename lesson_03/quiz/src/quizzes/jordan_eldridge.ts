import {
  AnswerChoice,
  MultipleChoiceQuizQuestion,
  QuizQuestion,
  QuizQuestionProvider,
} from 'codedifferently-instructional';

export class JordanEldridgeQuiz implements QuizQuestionProvider {
  getProviderName(): string {
    return 'jordaneldridge';
  }

  makeQuizQuestions(): QuizQuestion[] {
    return [
      JordanEldridgeQuiz.makeQuestion0(), 
      JordanEldridgeQuiz.makeQuestion1(),
      JordanEldridgeQuiz.makeQuestion2()
    ];
  }

  private static makeQuestion0(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      0,
      'What does CPU stand for in computer terminology?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Computer Personal Unit'],
        [AnswerChoice.B, 'Central Processing Unit'],
        [AnswerChoice.C, 'Core Performance Utility'],
        [AnswerChoice.D, 'Central Program Unit'],
      ]),
      AnswerChoice.B,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion1(): QuizQuestion {
    return new MultipleChoiceQuizQuestion(
      1,
      'Which of the following is NOT a programming paradigm?',
      new Map<AnswerChoice, string>([
        [AnswerChoice.A, 'Object-Oriented Programming'],
        [AnswerChoice.B, 'Functional Programming'],
        [AnswerChoice.C, 'Sequential Programming'],
        [AnswerChoice.D, 'Procedural Programming'],
      ]),
      AnswerChoice.C,
    ); // Replace `UNANSWERED` with the correct answer.
  }

  private static makeQuestion2(): QuizQuestion {
    return new QuizQuestion(
      2,
      'What programming language is known for its use of indentation instead of curly braces?',
      'Python',
    ); // Provide an answer.
  }
}