<template>
  <div class="parties-container">
    <h1>Politieke Partijen in Nederland</h1>
    <div class="parties-grid">
      <div v-for="party in parties" :key="party.name" class="party-item">
        <img :src="party.image" :alt="party.name" class="party-logo" />
        <h2>{{ party.name }}</h2>
        <p><strong>Hoofd:</strong> {{ party.leader }}</p>
        <button @click="showMoreInfo(party)">Meer info</button>
      </div>
    </div>
    <div v-if="selectedParty" class="modal" @click.self="closeModal">
      <div class="modal-content">
        <h2>{{ selectedParty.name }}</h2>
        <img :src="selectedParty.image" :alt="selectedParty.name" class="modal-image" />
        <p><strong>Hoofd:</strong> {{ selectedParty.leader }}</p>
        <p><strong>Beschrijving:</strong> {{ selectedParty.description }}</p>
        <p><strong>Belangrijke Standpunten:</strong></p>
        <ul>
          <li v-for="(point, index) in selectedParty.keyPoints" :key="index">{{ point }}</li>
        </ul>
        <button @click="closeModal">Sluiten</button>
      </div>
    </div>
  </div>

  <div class="parties">
    <footer class="footer">
      <div class="footer-content">
        <div class="footer-section">
          <h3>Contact</h3>
          <p>Email: info@politiekgids.nl</p>
          <p>Telefoon: +31 20 123 4567</p>
        </div>
        <div class="footer-section">
          <h3>Volg Ons</h3>
          <ul>
            <li><a href="#" class="social-link">Facebook</a></li>
            <li><a href="#" class="social-link">Twitter</a></li>
            <li><a href="#" class="social-link">Instagram</a></li>
          </ul>
        </div>
        <div class="footer-section">
          <h3>Informatie</h3>
          <ul>
            <li><a href="#" class="footer-link">Privacybeleid</a></li>
            <li><a href="#" class="footer-link">Algemene Voorwaarden</a></li>
            <li><a href="#" class="footer-link">Cookiebeleid</a></li>
          </ul>
        </div>
      </div>
      <div class="footer-bottom">
        <p>&copy; 2025 Politieke Gids. Alle rechten voorbehouden.</p>
      </div>
    </footer>
  </div>
</template>

<script>
import logoPVV from '@/assets/img/logo-pvv.jpg';
import logoGroenLinks from '@/assets/img/groenlinks.png';
import logoVVD from '@/assets/img/vvd.png';
import logoD66 from '@/assets/img/d66.png';
import logoCDA from '@/assets/img/cda.png';
import logoSP from '@/assets/img/sp.png';
import logoPVDAG from '@/assets/img/pvdag.png';
import logoSGP from '@/assets/img/sgp.png';
import logo50Plus from '@/assets/img/50plus.png';
import logoDENK from '@/assets/img/denk.png';
import logoPvdD from '@/assets/img/pvdd.png';
import logoFVD from '@/assets/img/fvd.png';

export default {
  data() {
    return {
      parties: [
        {
          name: 'VVD',
          leader: 'Dylan Yesilgöz',
          description: 'Vrijheid en verantwoordelijkheid, economische groei, en een kleinere overheid.',
          keyPoints: [
            'Economische groei stimuleren',
            'Focus op nationale veiligheid',
            'Meer werkgelegenheid',
          ],
          image: logoVVD,
        },
        {
          name: 'D66',
          leader: 'Rob Jetten',
          description: 'Progressieve waarden, klimaatverandering, en onderwijsvernieuwing.',
          keyPoints: [
            'Investeren in onderwijs',
            'Klimaatbeleid versterken',
            'Meer gelijkheid in de samenleving',
          ],
          image: logoD66,
        },
        {
          name: 'PVV',
          leader: 'Geert Wilders',
          description: 'Streng immigratiebeleid en een kritische houding ten opzichte van de EU.',
          keyPoints: [
            'Beperken immigratie',
            'Meer politie op straat',
            'Strengere aanpak van criminaliteit',
          ],
          image: logoPVV,
        },
        {
          name: 'GroenLinks',
          leader: 'Jesse Klaver',
          description: 'Duurzaamheid en sociale gelijkheid staan centraal in het beleid.',
          keyPoints: [
            'Klimaatverandering aanpakken',
            'Eerlijke verdeling van rijkdom',
            'Investeren in schone energie',
          ],
          image: logoGroenLinks,
        },
        {
          name: 'CDA',
          leader: 'Wopke Hoekstra',
          description: 'Ouderlijke waarden, aandacht voor het gezin en het midden- en kleinbedrijf.',
          keyPoints: [
            'Steunen van het MKB',
            'Investeren in het gezinsbeleid',
            'Veiligheid in buurten verbeteren',
          ],
          image: logoCDA,
        },
        {
          name: 'SP',
          leader: 'Lilian Marijnissen',
          description: 'Socialistische partij die zich richt op armoedebestrijding en gelijke kansen.',
          keyPoints: [
            'Strijd tegen armoede',
            'Verbeteren van de gezondheidszorg',
            'Gelijke kansen voor iedereen',
          ],
          image: logoSP,
        },
        {
          name: 'PVDAG',
          leader: 'Kees van der Staaij',
          description: 'Conservatief-christelijke partij die zich richt op morele waarden en gezin.',
          keyPoints: [
            'Waarde van gezin centraal stellen',
            'Behoud van christelijke normen en waarden',
            'Investeren in basisbeveiliging',
          ],
          image: logoPVDAG,
        },
        {
          name: 'SGP',
          leader: 'Kees van der Staaij',
          description: 'Christelijk-politieke partij die zich inzet voor de Bijbel en het gezinsbeleid.',
          keyPoints: [
            'Zorg voor de zwakken in de samenleving',
            'Behoud van christelijke normen',
            'Aandacht voor het milieu',
          ],
          image: logoSGP,
        },
        {
          name: '50PLUS',
          leader: 'Jan Nagel',
          description: 'Focus op de rechten van ouderen en verbetering van hun levenskwaliteit.',
          keyPoints: [
            'Pensioen voor ouderen verbeteren',
            'Gezondheidszorg voor ouderen verbeteren',
            'Veiligheid voor ouderen vergroten',
          ],
          image: logo50Plus,
        },
        {
          name: 'DENK',
          leader: 'Tunahan Kuzu',
          description: 'Politieke partij die zich richt op de positie van minderheden in Nederland.',
          keyPoints: [
            'Gelijke kansen voor minderheden',
            'Verbeteren integratiebeleid',
            'Leren van andere culturen',
          ],
          image: logoDENK,
        },
        {
          name: 'Partij voor de Dieren',
          leader: 'Caroline van der Plas',
          description: 'Zorg voor dierenwelzijn, milieu en de natuur staan centraal.',
          keyPoints: [
            'Dierenrechten beschermen',
            'Klimaatverandering aanpakken',
            'Behoud van natuur en biodiversiteit',
          ],
          image: logoPvdD,
        },
        {
          name: 'Forum voor Democratie',
          leader: 'Thierry Baudet',
          description: 'Conservatief en eurosceptisch met nadruk op nationale soevereiniteit.',
          keyPoints: [
            'Afname van belastingdruk',
            'Eurosceptisch beleid',
            'Versterking van de nationale identiteit',
          ],
          image: logoFVD,
        },
      ],
      selectedParty: null,
    };
  },
  methods: {
    showMoreInfo(party) {
      this.selectedParty = party;
    },
    closeModal() {
      this.selectedParty = null;
    },
  },
};
</script>

<style scoped>
/* Stijlen zoals eerder gegeven blijven hetzelfde */
</style>
