<template>
  <div class="parties-container">
    <h1>Partijen van Nederland</h1>
    <div class="provinces-grid">
      <div v-for="province in provinces" :key="province.name" class="province-item">
        <h2>{{ province.name }}</h2>
        <div class="parties-list">
          <div v-for="party in province.parties" :key="party.name" class="party-item">
            <h3>{{ party.name }}</h3>
            <p><strong>Lijsttrekker:</strong> {{ party.leader }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      provinces: [
        {
          name: 'Noord-Holland',
          parties: [
            { name: 'VVD', leader: 'Mark Rutte' },
            { name: 'D66', leader: 'Sjoerd Sjoerdsma' },
            { name: 'PVV', leader: 'Geert Wilders' },
            { name: 'GroenLinks', leader: 'Jesse Klaver' },
            { name: 'PvdA', leader: 'Lilianne Ploumen' },
            { name: 'SP', leader: 'Marijnissen' },
          ],
        },
        {
          name: 'Zuid-Holland',
          parties: [
            { name: 'VVD', leader: 'Bram van Ojik' },
            { name: 'D66', leader: 'Jan van Zanen' },
            { name: 'PVV', leader: 'Geert Wilders' },
            { name: 'GroenLinks', leader: 'Vera Bergkamp' },
            { name: 'PvdA', leader: 'Khalid M. Ali' },
            { name: 'SP', leader: 'Lilian Marijnissen' },
          ],
        },
        {
          name: 'Utrecht',
          parties: [
            { name: 'VVD', leader: 'Fleur Agema' },
            { name: 'D66', leader: 'Jesse Klaver' },
            { name: 'PVV', leader: 'Geert Wilders' },
            { name: 'GroenLinks', leader: 'Bram van Ojik' },
            { name: 'PvdA', leader: 'John Kerstens' },
            { name: 'SP', leader: 'Renske Leijten' },
          ],
        },
        {
          name: 'Noord-Brabant',
          parties: [
            { name: 'VVD', leader: 'Joost van der Meer' },
            { name: 'D66', leader: 'Sjoerd Sjoerdsma' },
            { name: 'PVV', leader: 'Geert Wilders' },
            { name: 'GroenLinks', leader: 'Jesse Klaver' },
            { name: 'PvdA', leader: 'Thijs Reuten' },
            { name: 'SP', leader: 'Ron Meyer' },
          ],
        },
        {
          name: 'Limburg',
          parties: [
            { name: 'VVD', leader: 'Bram van Ojik' },
            { name: 'D66', leader: 'Wouter Koolmees' },
            { name: 'PVV', leader: 'Geert Wilders' },
            { name: 'GroenLinks', leader: 'Laura Bromet' },
            { name: 'PvdA', leader: 'Wouter Koolmees' },
            { name: 'SP', leader: 'Ron Meyer' },
          ],
        },
        {
          name: 'Gelderland',
          parties: [
            { name: 'VVD', leader: 'Mark Rutte' },
            { name: 'D66', leader: 'Sjoerd Sjoerdsma' },
            { name: 'PVV', leader: 'Geert Wilders' },
            { name: 'GroenLinks', leader: 'Jesse Klaver' },
            { name: 'PvdA', leader: 'Paul Depla' },
            { name: 'SP', leader: 'Renske Leijten' },
          ],
        },
        {
          name: 'Flevoland',
          parties: [
            { name: 'VVD', leader: 'Fleur Agema' },
            { name: 'D66', leader: 'Jesse Klaver' },
            { name: 'PVV', leader: 'Geert Wilders' },
            { name: 'GroenLinks', leader: 'Bram van Ojik' },
            { name: 'PvdA', leader: 'Jeroen Dijsselbloem' },
            { name: 'SP', leader: 'Ron Meyer' },
          ],
        },
        {
          name: 'Drenthe',
          parties: [
            { name: 'VVD', leader: 'Bram van Ojik' },
            { name: 'D66', leader: 'Jesse Klaver' },
            { name: 'PVV', leader: 'Geert Wilders' },
            { name: 'GroenLinks', leader: 'Bram van Ojik' },
            { name: 'PvdA', leader: 'Henk Nijhof' },
            { name: 'SP', leader: 'Renske Leijten' },
          ],
        },
        {
          name: 'Overijssel',
          parties: [
            { name: 'VVD', leader: 'Mark Rutte' },
            { name: 'D66', leader: 'Sjoerd Sjoerdsma' },
            { name: 'PVV', leader: 'Geert Wilders' },
            { name: 'GroenLinks', leader: 'Jesse Klaver' },
            { name: 'PvdA', leader: 'Henk Nijhof' },
            { name: 'SP', leader: 'Renske Leijten' },
          ],
        },
        {
          name: 'Friesland',
          parties: [
            { name: 'VVD', leader: 'Fleur Agema' },
            { name: 'D66', leader: 'Wouter Koolmees' },
            { name: 'PVV', leader: 'Geert Wilders' },
            { name: 'GroenLinks', leader: 'Bram van Ojik' },
            { name: 'PvdA', leader: 'Wouter Koolmees' },
            { name: 'SP', leader: 'Ron Meyer' },
          ],
        },
      ],
    };
  },
};
</script>

<style scoped>
.parties-container {
  max-width: 1200px;
  margin: 20px auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  font-family: 'Arial', sans-serif;
}

h1 {
  text-align: center;
  font-size: 2rem;
  margin-bottom: 20px;
  color: #333;
}

.provinces-grid {
  display: grid;
  grid-template-columns: repeat(5, 1fr); /* 5 kolommen */
  gap: 20px; /* Ruimte tussen items */
}

.province-item {
  background-color: #fff;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
}

h2 {
  color: #333;
  font-size: 1.5rem;
  margin-bottom: 10px;
}

.parties-list {
  margin-top: 10px;
}

.party-item {
  margin-top: 10px; /* Ruimte tussen partij items */
  padding: 10px;
  border-left: 4px solid #ffd700; /* Linker rand voor partij item */
  background-color: #fff; /* Witte achtergrond voor partij item */
}

.party-item h3 {
  color: #333;
  font-size: 1rem;
}

.party-item p {
  color: #555;
  font-size: 1rem;
  line-height: 0.9;
}
</style>
