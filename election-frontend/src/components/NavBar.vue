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
          <router-link :to="link.url" @click="activeLink = link.name">{{ link.name }}</router-link>
        </li>
      </ul>

      <div class="buttons">
        <div v-if="authState.isAuthenticated" class="signout-btn">
          <button @click="signOut">Sign Out</button>
        </div>

        <div v-else>
          <div class="login-btn" v-for="(button, index) in authButtons" :key="index">
            <router-link :to="button.url" @click="activeButton = button.name">
              <button :class="{ active: activeButton === button.name }">{{ button.name }}</button>
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </nav>
</template>

<script lang="ts">
import { inject } from 'vue';
import { authState } from '@/router/auth'; // Import the auth state

export default {
  setup() {
    return { authState }; // Make authState available in the template
  },

  data() {
    return {
      navLinks: [
        { name: "Home", url: "/" },
        { name: "Election result", url: "/election-result" },
        { name: "Parties", url: "/parties" },
        { name: "Quiz", url: "/quiz" },
        { name: "Forum", url: "/forum" }, // Added Forum link
        { name: "Contact us", url: "/contact-us" }
      ],

      authButtons: [
        { name: "Login", url: "/login" },
        { name: "Register", url: "/register" }
      ],

      activeLink: "Home", // Set default active link
      activeButton: "Login" // Set default active button
    };
  },

  methods: {
    signOut() {
      authState.logout(); // Call logout method from authState
      this.$router.push('/login'); // Redirect to login page
    }
  }
};
</script>

<style>
/* General Navbar Styling */
/* General Navbar Styling */
.navbar {
  background-color: #002f6c;
  padding: 10px 30px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
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
  width: 50px;
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
  padding: 10px 15px;
  color: white;
  text-decoration: none;
  font-weight: bold;
  transition: color 0.3s ease, border-bottom 0.3s ease; /* Added transition for border */
  font-size: 1.1em;
}

.nav-link a:hover,
.nav-link.active a {
  color: #ffd700; /* Gold color on hover */
  border-bottom: 2px solid #ffd700; /* Gold underline on hover */
}

/* Search Bar */
.search {
  position: relative;
  display: flex;
  align-items: center;
  width: 200px;
}

.search input {
  padding: 8px 40px 8px 15px;
  width: 100%;
  height: 40px;
  border: none;
  border-radius: 20px;
  font-size: 1em;
  outline: none;
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
  gap: 25px; /* Increased gap for space between buttons */
  align-items: center; /* Align buttons vertically in the middle */
}

.login-btn,
.signout-btn { /* Container for buttons (Login, Register, Sign Out) */
  display: inline-block;  /* Ensure each button stays inline */
}

.login-btn button,
.signout-btn button {
  padding: 10px 20px;
  font-size: 1em;
  font-weight: bold;
  border-radius: 20px;
  cursor: pointer;
  transition: background-color 0.3s ease, color 0.3s ease; /* Transition for background color */
  border: 2px solid white;
  background-color: transparent;
  color: white;
}

.login-btn button:hover,
.signout-btn button:hover,
.login-btn button.active,
.signout-btn button.active {
  background-color: white; /* Active button background */
  color: #002f6c; /* Active button text color */
}

/* Active Button */
button.active {
  background-color: white; /* Active button background */
  color: #002f6c; /* Active button text color */
  border-color: #002f6c; /* Match border color to the active state */
}

/* Burger Menu */
.burger {
  display: none; /* Hidden by default */
  flex-direction: column;
  cursor: pointer;
}

.burger .line {
  width: 25px;
  height: 3px;
  background-color: white;
  margin: 4px 0;
}

/* Mobile Menu Styling */
.mobile-menu {
  background-color: #002f6c;
  position: absolute;
  top: 85px;
  display: flex;
  justify-content: center;
  flex-direction: column;
  width: 100%;
  box-shadow: 0px 8px 10px rgba(0, 0, 0, 0.1);
}

.mobile-menu-nav {
  list-style: none;
  white-space: nowrap;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 10px 0;
}

.mobile-nav-link {
  width: 100%;
  text-align: center;
  margin: 10px 0;
}

.mobile-nav-link a {
  color: white;
  text-decoration: none;
  padding: 10px;
  font-size: 1.1em;
}

.mobile-nav-link a:hover {
  background-color: #ffd700; /* Change background on hover */
  border-radius: 20px;
  color: #002f6c; /* Change text color on hover */
}

/* Responsive Design */
@media (max-width: 1100px) {
  .nav-links {
    display: none; /* Hide nav links on mobile */
  }

  .burger {
    display: flex; /* Show burger icon on mobile */
  }

  .search {
    width: 50%; /* Adjust search bar width */
    margin: 0;
  }

  .search input {
    width: 100%; /* Make input full width */
  }

  .buttons {
    display: none; /* Hide buttons on mobile */
  }

  .mobile-menu {
    display: flex; /* Show mobile menu */
  }
}
</style>
