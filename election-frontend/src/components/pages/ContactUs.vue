<template>
  <div class="contact-container">
    <h1>Neem Contact Op</h1>
    <form @submit.prevent="handleSubmit" class="contact-form">
      <div class="form-group">
        <label for="name">Naam</label>
        <input
            type="text"
            id="name"
            v-model="formData.name"
            required
            placeholder="Voer uw naam in"
        />
      </div>
      <div class="form-group">
        <label for="email">E-mailadres</label>
        <input
            type="email"
            id="email"
            v-model="formData.email"
            required
            placeholder="Voer uw e-mailadres in"
        />
      </div>
      <div class="form-group">
        <label for="subject">Onderwerp</label>
        <input
            type="text"
            id="subject"
            v-model="formData.subject"
            required
            placeholder="Voer het onderwerp in"
        />
      </div>
      <div class="form-group">
        <label for="message">Bericht</label>
        <textarea
            id="message"
            v-model="formData.message"
            required
            placeholder="Voer uw bericht in"
        ></textarea>
      </div>
      <button type="submit" class="submit-btn">Verstuur</button>
    </form>
  </div>
</template>

<script lang="ts">
export default {
  data() {
    return {
      formData: {
        name: '',
        email: '',
        subject: '',
        message: '',
      },
    };
  },
  methods: {
    handleSubmit() {
      // Handle form submission logic (e.g., send to API)
      console.log('Form submitted:', this.formData);
      // Reset the form
      this.formData = {
        name: '',
        email: '',
        subject: '',
        message: '',
      };
    },
  },
};
</script>

<style scoped>
.contact-container {
  display: flex;
  flex-direction: column;
  justify-content: center; /* Center vertically */
  align-items: center; /* Center horizontally */
  max-width: 600px;
  margin: 50px auto; /* Center horizontally with margin at top */
  padding: 20px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
}

h1 {
  text-align: center;
  color: #002f6c; /* Header color */
  margin-bottom: 20px;
}

.contact-form {
  display: flex;
  flex-direction: column;
  gap: 15px; /* Space between form elements */
  width: 100%; /* Allow form to fill container */
}

.form-group {
  display: flex;
  flex-direction: column;
}

label {
  font-weight: bold;
  margin-bottom: 5px;
  color: #002f6c; /* Label color */
}

input,
textarea {
  padding: 10px;
  border: 2px solid #002f6c; /* Border color */
  border-radius: 5px;
  font-size: 1em;
  outline: none;
  transition: border-color 0.3s ease;
}

input:focus,
textarea:focus {
  border-color: #ffd700; /* Focus border color */
}

textarea {
  resize: vertical; /* Allow vertical resizing */
  min-height: 100px; /* Minimum height */
}

.submit-btn {
  padding: 10px;
  border: none;
  border-radius: 5px;
  background-color: #ffd700; /* Button background color */
  color: #002f6c; /* Button text color */
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s ease, color 0.3s ease;
}

.submit-btn:hover {
  background-color: #002f6c; /* Button hover background */
  color: white; /* Button hover text color */
}
</style>
