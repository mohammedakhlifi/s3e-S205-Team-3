<template>
  <div class="app-container">
    <!-- Container voor de quiz en grafiek -->
    <div class="quiz-chart-container">
      <!-- Quiz gedeelte -->
      <div class="quiz-section">
        <h1>Stemwijzer Quiz</h1>
        <div v-if="!showResults">
          <h3>{{ currentQuestion?.question || 'Geen vraag gevonden' }}</h3>
          <div class="options">
            <button
                v-for="(option, index) in options"
                :key="index"
                :class="['option-button', index < 2 ? 'agree' : 'disagree']"
                @click="selectAnswer(index)"
            >
              {{ option }}
            </button>
          </div>
        </div>
        <div v-else>
          <h2>Uitslag</h2>
          <p>De partij die het beste bij jouw keuzes past: <strong>{{ getBestMatch() }}</strong></p>
          <button class="retry-button" @click="resetQuiz">Doe de quiz opnieuw</button>
        </div>
      </div>

      <!-- Dynamische grafiek gedeelte -->
      <div class="chart-section">
        <canvas id="resultChart"></canvas>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref, reactive, computed } from 'vue';
import { Chart, DoughnutController, ArcElement, Tooltip, Legend } from 'chart.js';

Chart.register(DoughnutController, ArcElement, Tooltip, Legend);

export default defineComponent({
  name: 'App',
  setup() {
    const allQuestions = [
      { question: 'Nederland moet meer investeren in duurzame energie.', answers: [1, 2, 3] },
      { question: 'De belastingen voor de rijksten moeten omhoog.', answers: [2, 1, 3] },
      { question: 'Nederland moet uit de Europese Unie stappen.', answers: [3, 2, 1] },
      { question: 'We moeten investeren in defensie.', answers: [3, 2, 1] },
      { question: 'Het minimumloon moet fors omhoog.', answers: [1, 2, 3] },
      { question: 'Er moet een nationale zorgverzekering komen.', answers: [2, 1, 3] },
      { question: 'Het woningaanbod moet worden uitgebreid.', answers: [1, 2, 3] },
      { question: 'Nederland moet meer vluchtelingen opnemen.', answers: [2, 1, 3] },
      { question: 'Het gebruik van fossiele brandstoffen moet verminderen.', answers: [1, 2, 3] },
      { question: 'Er moet meer geld naar onderwijs.', answers: [1, 2, 3] },
      { question: 'De gezondheidszorg moet worden geprivatiseerd.', answers: [3, 2, 1] },
      { question: 'Er moeten strengere immigratieregels komen.', answers: [3, 1, 2] },
      { question: 'Zware criminaliteit moet zwaarder bestraft worden.', answers: [3, 2, 1] },
      { question: 'De overheid moet subsidies geven aan bedrijven.', answers: [2, 3, 1] },
      { question: 'Er moet een basisinkomen komen voor iedereen.', answers: [1, 2, 3] },
      { question: 'De woningmarkt moet vrijgegeven worden.', answers: [3, 2, 1] },
      { question: 'Er moet meer budget voor cultuur.', answers: [1, 2, 3] },
      { question: 'De klimaatdoelen moeten aangescherpt worden.', answers: [1, 3, 2] },
      { question: 'Het pensioenstelsel moet hervormd worden.', answers: [2, 1, 3] },
      { question: 'De politie moet meer bevoegdheden krijgen.', answers: [3, 2, 1] }
    ];

    const parties = reactive({
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
    });

    const options = ['Helemaal eens', 'Eens', 'Oneens', 'Helemaal oneens'];
    const currentQuestionIndex = ref(0);
    const showResults = ref(false);
    let chart = null;

    // Compute the current question
    const currentQuestion = computed(() => allQuestions[currentQuestionIndex.value]);

    const selectAnswer = (answerIndex) => {
      const partyIndex = currentQuestion.value.answers[answerIndex];
      const partyNames = Object.keys(parties);
      if (partyIndex && partyIndex <= partyNames.length) {
        parties[partyNames[partyIndex - 1]]++;
      }
      updateChart();
      if (currentQuestionIndex.value < allQuestions.length - 1) {
        currentQuestionIndex.value++;
      } else {
        showResults.value = true;
      }
    };

    const getBestMatch = () => {
      return Object.entries(parties).reduce((a, b) => (b[1] > a[1] ? b : a))[0];
    };

    const resetQuiz = () => {
      for (const party in parties) parties[party] = 0;
      currentQuestionIndex.value = 0;
      showResults.value = false;
      createChart();
    };

    const createChart = () => {
      const ctx = document.getElementById('resultChart').getContext('2d');
      if (!ctx) return;
      const data = {
        labels: Object.keys(parties),
        datasets: [
          {
            data: Object.values(parties),
            backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF', '#FF9F40', '#B4E8C1', '#76D7C4', '#F5B7B1', '#85C1E9', '#F7DC6F']
          }
        ]
      };
      if (chart) chart.destroy();
      chart = new Chart(ctx, {
        type: 'doughnut',
        data,
        options: { responsive: true, plugins: { legend: { position: 'bottom' } } }
      });
    };

    const updateChart = () => {
      if (chart) {
        chart.data.datasets[0].data = Object.values(parties);
        chart.update();
      }
    };

    onMounted(createChart);

    return {
      currentQuestion,
      options,
      showResults,
      selectAnswer,
      getBestMatch,
      resetQuiz
    };
  }
});
</script>

<style scoped>
body {
  margin: 0;
  padding: 0;
  overflow: hidden; /* Zorg ervoor dat de pagina niet scrollt */
  background: #f0f4f7;
}

.app-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 50vh; /* Zorg ervoor dat het in het midden van het scherm staat */
  padding: 20px;
}

.quiz-chart-container {
  display: flex;
  gap: 50px;
  background: #fff;
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
  max-width: 900px;
  width: 100%;
}

.quiz-section {
  max-width: 350px;
}

h1 {
  font-size: 2rem;
  margin-bottom: 20px;
  font-weight: bold;
}

h3 {
  font-size: 1.2rem;
  margin-bottom: 15px;
}

.options {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.option-button {
  padding: 10px 20px;
  border: none;
  border-radius: 8px;
  font-size: 1rem;
  transition: background-color 0.3s;
}

.option-button.agree {
  background-color: #28a745;
  color: white;
}

.option-button.agree:hover {
  background-color: #218838;
}

.option-button.disagree {
  background-color: #dc3545;
  color: white;
}

.option-button.disagree:hover {
  background-color: #c82333;
}

.retry-button {
  margin-top: 20px;
  padding: 10px 20px;
  font-size: 1rem;
  border: none;
  border-radius: 8px;
  background-color: #007bff;
  color: white;
}

.retry-button:hover {
  background-color: #0056b3;
}

.chart-section {
  flex: 1;
  max-width: 400px;
}
</style>