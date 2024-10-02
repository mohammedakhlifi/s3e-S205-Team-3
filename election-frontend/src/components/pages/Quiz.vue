<template>
  <div class="quiz-container">
    <h1>Stemwijzer Quiz</h1>

    <div v-if="!showResults">
      <!-- Vraag -->
      <h3>{{ questions[currentQuestionIndex].question }}</h3>

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
      questions: [
        {
          question: "Nederland moet meer investeren in duurzame energie.",
          answers: [1, 2, 3] // 1 = D66, 2 = GroenLinks, 3 = VVD
        },
        {
          question: "De belastingen voor de rijksten moeten omhoog.",
          answers: [2, 1, 3] // 2 = GroenLinks, 1 = SP, 3 = VVD
        },
        {
          question: "Nederland moet uit de Europese Unie stappen.",
          answers: [3, 2, 1] // 3 = PVV, 2 = FvD, 1 = VVD
        },
        {
          question: "We moeten investeren in defensie en onze krijgsmacht uitbreiden.",
          answers: [3, 2, 1] // 3 = VVD, 2 = CDA, 1 = D66
        },
        {
          question: "Het minimumloon moet fors omhoog.",
          answers: [1, 2, 3] // 1 = SP, 2 = PvdA, 3 = VVD
        },
        {
          question: "Er moet een nationale zorgverzekering komen.",
          answers: [2, 1, 3] // 2 = GroenLinks, 1 = SP, 3 = VVD
        },
        {
          question: "Het woningaanbod moet worden uitgebreid met betaalbare woningen.",
          answers: [1, 2, 3] // 1 = PvdA, 2 = D66, 3 = VVD
        },
        {
          question: "Nederland moet meer vluchtelingen opnemen.",
          answers: [2, 1, 3] // 2 = D66, 1 = GroenLinks, 3 = PVV
        },
        {
          question: "Het gebruik van fossiele brandstoffen moet sterk verminderd worden.",
          answers: [1, 2, 3] // 1 = GroenLinks, 2 = D66, 3 = VVD
        },
        {
          question: "Er moet meer geld naar onderwijs.",
          answers: [1, 2, 3] // 1 = PvdA, 2 = D66, 3 = VVD
        }
      ],
      userAnswers: [],
      currentQuestionIndex: 0,
      showResults: false,
      parties: {
        D66: 0,
        GroenLinks: 0,
        VVD: 0,
        PVV: 0,
        SP: 0,
        PvdA: 0,
        FvD: 0,
        CDA: 0
      },
      options: ["Helemaal eens", "Eens", "Oneens", "Helemaal oneens"]
    };
  },
  methods: {
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
        D66: 0,
        GroenLinks: 0,
        VVD: 0,
        PVV: 0,
        SP: 0,
        PvdA: 0,
        FvD: 0,
        CDA: 0
      };
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
  max-width: 100vw; /* Zorgt ervoor dat er geen horizontale scrolling is */
  overflow: hidden;
  text-align: center;
  padding: 10px;
}

h1 {
  margin-bottom: 10px;
  font-size: 1.5rem; /* Verkleind om alles passend te houden */
}

h3 {
  margin-bottom: 10px;
  font-size: 1.2rem; /* Verkleind om alles passend te houden */
}

.options {
  display: flex;
  justify-content: center;
  gap: 10px;
}

button {
  padding: 8px 15px; /* Kleiner formaat voor knoppen */
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
  padding: 8px 15px; /* Kleiner formaat voor de resetknop */
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
  font-size: 1.2rem; /* Verkleind om alles passend te houden */
}
</style>
