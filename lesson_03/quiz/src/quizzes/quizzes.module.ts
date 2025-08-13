import { Module } from '@nestjs/common';
import { AnotherQuiz } from './another_quiz.js';
import { AnthonyMaysQuiz } from './anthony_mays_quiz.js';
import { JordanEldridgeQuiz } from './jordan_eldridge.js';
import { BrooklynHardenQuiz } from './brooklyn_harden_quiz.js';
import { JordanEldridgeQuiz } from './jordan_eldridge.js';
export const Quizzes = Symbol.for('Quizzes');

// Add your quiz provider here.

<<<<<<< HEAD
const QUIZ_PROVIDERS = [AnthonyMaysQuiz, AnotherQuiz, BrooklynHardenQuiz,JordanEldridgeQuiz];
=======

const QUIZ_PROVIDERS = [
  AnthonyMaysQuiz,
  AnotherQuiz,
  BrooklynHardenQuiz,
  JordanEldridgeQuiz

];
>>>>>>> 774e821 (feat: added brooklyns changes and fixed merge conflicts)

@Module({
  providers: [
    ...QUIZ_PROVIDERS,
    {
      provide: Quizzes,
      useFactory: (...args) => [...args],
      inject: QUIZ_PROVIDERS,
    },
  ],
})
export class QuizzesModule {}
