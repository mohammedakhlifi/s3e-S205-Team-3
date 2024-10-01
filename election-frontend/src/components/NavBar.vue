<template>
  <nav class="navbar">

    <div class="navbar-container">

      <div class="logo">
        <img src="./icons/political-party.png" alt="logo" />
      </div>

      <ul class="nav-links">
        <li
            class="nav-link"
            v-for="(link, index) in navLinks"
            :key="index"
            :class="{ active: activeLink === link.name }"
        >
          <router-link :to="link.url">{{ link.name }}</router-link>
        </li>
      </ul>

      <div class="search">
        <input type="search" id="search" name="search" placeholder="Search" />
        <img src="./icons/search-icon.png" alt="search-icon" />
      </div>

      <div class="buttons">
        <div class="login-btn" v-for="(button, index) in authButtons" :key="index">
          <router-link :to="button.url">
            <button :class="{ active: activeButton === button.name }">{{ button.name }}</button>
          </router-link>
        </div>
      </div>

      <div class="burger" @click="toggleMobileMenu">
        <div class="line" v-for="n in 3" :key="n"></div>
      </div>

    </div>

    <div v-if="isMobileMenuOpen" class="mobile-menu">

      <ul class="mobile-menu-nav">
        <li
            class="mobile-nav-link"
            v-for="(link, index) in navLinks"
            :key="index"
            @click="closeMobileMenu()"
        >
          <router-link :to="link.url">{{ link.name }}</router-link>
        </li>
      </ul>

      <div class="mobile-buttons">

        <div class="login-btn" v-for="(button, index) in authButtons" :key="index">
          <router-link :to="button.url">
            <button :class="{ active: activeButton === button.name }">{{ button.name }}</button>
          </router-link>
        </div>

      </div>

    </div>

  </nav>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import { useRoute } from 'vue-router';

export default defineComponent({
  data() {
    return {
      navLinks: [
        { name: "Home", url: "/" },
        { name: "Election result", url: "/election-result" },
        { name: "Parties", url: "/parties" },
        { name: "Chat", url: "/chat" },
        { name: "Contact us", url: "/contact-us" },
      ],

      authButtons: [
        { name: "Login", url: "/Login" },
        { name: "Register", url: "/Register" }
      ],

      isMobileMenuOpen: false,
      activeLink: "Home",
      activeButton: "Login",
    };
  },

  mounted() {
    const route = useRoute();
    this.updateActiveItems(route.path);
  },

  watch: {
    '$route'(to) {
      this.updateActiveItems(to.path);
    }
  },

  methods: {
    updateActiveItems(path, isButtonClick = false) {
      if (isButtonClick) {

        this.activeLink = "";

      } else {

        const matchingLink = this.navLinks.find(link => link.url === path);
        this.activeLink = matchingLink ? matchingLink.name : "";

      }

      const matchingButton = this.authButtons.find(button => button.url === path);
      this.activeButton = matchingButton ? matchingButton.name : "Login";
    },

    toggleMobileMenu() {
      this.isMobileMenuOpen = !this.isMobileMenuOpen;
    },

    closeMobileMenu() {
      this.isMobileMenuOpen = false;
    },
  }
});
</script>

<style>
/* General Navbar Styling */
.navbar {
  background-image: linear-gradient(darkblue, darkblue);
  padding: 10px 30px;
}

.navbar-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 0 auto;
  gap: 20px;
}

/* Logo */
.logo img {
  width: 60px;
}

/* Navigation Links */
.nav-links {
  display: flex;
  justify-content: center;
  flex-grow: 1;
  gap: 30px;
}

.nav-link {
  list-style: none;
  white-space: nowrap;
}

.nav-link a {
  padding: 10px;
  color: white;
  text-decoration: none;
  transition: color 0.3s ease;
}

.nav-link a:hover {
  color: darkgrey;
  background-color: rgba(255, 255, 255, 0.3);
  border-radius: 20px;
  border-bottom: 3px solid #000054;
}

/* Active Link Underline */
.nav-link.active a {
  border-bottom: 2px solid #000054;
}

/* Search Bar */
.search {
  position: relative;
  display: flex;
  align-items: center;
  width: 250px;
}

.search input {
  padding: 8px 40px 8px 15px;
  width: 100%;
  height: 40px;
  border: 2px solid rgba(129, 129, 129, 0.65);
  border-radius: 20px;
  font-size: 1em;
}

.search img {
  position: absolute;
  right: 15px;
  top: 50%;
  transform: translateY(-50%);
  width: 20px;
  height: 20px;
  pointer-events: none;
}

/* Buttons */
.buttons {
  display: flex;
  gap: 15px;
}

.login-btn button {
  padding: 10px 20px;
  font-size: 1em;
  font-weight: bold;
  border-radius: 10px;
  cursor: pointer;
  transition: background-color 0.3s ease, color 0.3s ease;
  border: 2px solid #000054;
  background-color: white;
}

.login-btn button:hover {
  background-color: #000054;
  color: white;
  transition: 0.5s;
}

/* Active Button Styles */
button.active {
  background-color: #000054;
  color: white;
}

/* Mobile Burger Menu */
.burger {
  display: none;
  flex-direction: column;
  cursor: pointer;
}

.burger .line {
  width: 25px;
  height: 3px;
  background-color: black;
  margin: 4px 0;
}

/* Mobile Menu Styling */
.mobile-menu {
  background-color: white;
  position: absolute;
  top: 85px;
  display: flex;
  justify-content: center;
  flex-direction: column;
  width: 100%;
}

.mobile-menu-nav {
  list-style: none;
  white-space: nowrap;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

.mobile-nav-link {
  width: 100%;
  text-align: center;
}

.mobile-nav-link a {
  color: black;
  text-decoration: none;
  margin: 10px 100px;

}

.mobile-nav-link a:hover {
  text-decoration: underline;
}

/* Responsive Design */
@media (max-width: 1100px) {
  .nav-links {
    display: none;
  }

  .burger {
    display: flex;
  }

  .search {
    width: 50%;
    margin: 0;
  }

  .search input {
    width: 100%;
  }

  .search img {
    right: 10px;
    width: 18px;
    height: 18px;
  }

  .buttons {
    display: none;
  }

  .mobile-menu {
    display: flex;
  }
}
</style>