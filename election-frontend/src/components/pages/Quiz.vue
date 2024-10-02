<template>
  <div class="quiz-container">
    <h1>Stemwijzer Quiz</h1>

    <div v-if="!showResults">
      <!-- Vraag -->
      <h3>{{ currentQuestion.question }}</h3>

      <!-- Opties -->
      <div class="options">
        <button
            v-for="(option, index) in options"
            :key="index"
            :class="{'agree': index === 0 || index === 1, 'disagree': index === 2 || index === 3}"
            @click="selectAnswer(index)"
        >
          {{ option }}
        </button>
      </div>
    </div>

    <!-- Resultaten -->
    <div v-else>
      <h2>Uitslag</h2>
      <p>De partij die het beste bij jouw keuzes past: <strong>{{ getBestMatch() }}</strong></p>
      <button class="retry-button" @click="resetQuiz">Doe de quiz opnieuw</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      allQuestions: [
        {
          question: "Nederland moet meer investeren in duurzame energie.",
          answers: [1, 2, 3]
        },
        {
          question: "De belastingen voor de rijksten moeten omhoog.",
          answers: [2, 1, 3]
        },
        {
          question: "Nederland moet uit de Europese Unie stappen.",
          answers: [3, 2, 1]
        },
        {
          question: "We moeten investeren in defensie en onze krijgsmacht uitbreiden.",
          answers: [3, 2, 1]
        },
        {
          question: "Het minimumloon moet fors omhoog.",
          answers: [1, 2, 3]
        },
        {
          question: "Er moet een nationale zorgverzekering komen.",
          answers: [2, 1, 3]
        },
        {
          question: "Het woningaanbod moet worden uitgebreid met betaalbare woningen.",
          answers: [1, 2, 3]
        },
        {
          question: "Nederland moet meer vluchtelingen opnemen.",
          answers: [2, 1, 3]
        },
        {
          question: "Het gebruik van fossiele brandstoffen moet sterk verminderd worden.",
          answers: [1, 2, 3]
        },
        {
          question: "Er moet meer geld naar onderwijs.",
          answers: [1, 2, 3]
        },
        {
          question: "De gezondheidszorg moet worden geprivatiseerd.",
          answers: [3, 2, 1]
        },
        {
          question: "Er moeten strengere immigratieregels komen.",
          answers: [3, 1, 2]
        },
        {
          question: "Zware criminaliteit moet zwaarder bestraft worden.",
          answers: [3, 2, 1]
        }
        // Voeg hier meer vragen toe voor variatie
      ],
      questions: [],
      userAnswers: [],
      currentQuestionIndex: 0,
      showResults: false,
      parties: {
        VVD: 0,
        D66: 0,
        GroenLinks: 0,
        PVV: 0,
        SP: 0,
        PvdA: 0,
        FvD: 0,
        CDA: 0,
        CU: 0,
        SGP: 0,
        PvdD: 0
      },
      options: ["Helemaal eens", "Eens", "Oneens", "Helemaal oneens"]
    };
  },
  created() {
    // Selecteer 10 willekeurige vragen uit de allQuestions pool
    this.questions = this.shuffleQuestions(this.allQuestions, 10);
  },
  methods: {
    shuffleQuestions(questions, count) {
      // Shuffle de vragen en neem de eerste 'count' vragen
      const shuffled = questions.sort(() => Math.random() - 0.5);
      return shuffled.slice(0, count);
    },
    selectAnswer(answerIndex) {
      // Sla het antwoord van de gebruiker op
      this.userAnswers[this.currentQuestionIndex] = answerIndex;

      // Voeg punten toe aan de partij die bij het antwoord hoort
      const partyIndex = this.questions[this.currentQuestionIndex].answers[answerIndex];
      const partyNames = Object.keys(this.parties);
      this.parties[partyNames[partyIndex - 1]]++;

      // Ga naar de volgende vraag
      if (this.currentQuestionIndex < this.questions.length - 1) {
        this.currentQuestionIndex++;
      } else {
        this.showResults = true;
      }
    },
    getBestMatch() {
      // Bepaal welke partij de meeste punten heeft
      let bestParty = "";
      let maxPoints = 0;
      for (const party in this.parties) {
        if (this.parties[party] > maxPoints) {
          maxPoints = this.parties[party];
          bestParty = party;
        }
      }
      return bestParty;
    },
    resetQuiz() {
      // Reset alle waarden om de quiz opnieuw te doen
      this.userAnswers = [];
      this.currentQuestionIndex = 0;
      this.showResults = false;
      this.parties = {
        VVD: 0,
        D66: 0,
        GroenLinks: 0,
        PVV: 0,
        SP: 0,
        PvdA: 0,
        FvD: 0,
        CDA: 0,
        CU: 0,
        SGP: 0,
        PvdD: 0
      };

      // Kies weer nieuwe willekeurige vragen
      this.questions = this.shuffleQuestions(this.allQuestions, 10);
    }
  },
  computed: {
    currentQuestion() {
      return this.questions[this.currentQuestionIndex];
    }
  }
};
</script>

<style scoped>
.quiz-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 70vh;
  max-width: 100vw;
  overflow: hidden;
  text-align: center;
  padding: 10px;
}

h1 {
  margin-bottom: 10px;
  font-size: 1.5rem;
}

h3 {
  margin-bottom: 10px;
  font-size: 1.2rem;
}

.options {
  display: flex;
  justify-content: center;
  gap: 10px;
}

button {
  padding: 8px 15px;
  font-size: 0.9rem;
  border: none;
  border-radius: 5px;
  color: white;
  cursor: pointer;
}

button.agree {
  background-color: #28a745;
}

button.agree:hover {
  background-color: #218838;
}

button.disagree {
  background-color: #dc3545;
}

button.disagree:hover {
  background-color: #c82333;
}

.retry-button {
  margin-top: 20px;
  padding: 8px 15px;
  font-size: 0.9rem;
  background-color: #007bff;
  border: none;
  border-radius: 5px;
  color: white;
  cursor: pointer;
}

.retry-button:hover {
  background-color: #0056b3;
}

p {
  text-align: center;
  font-size: 1.2rem;
}
</style>