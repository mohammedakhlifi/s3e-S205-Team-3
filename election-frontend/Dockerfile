# Gebruik een Node.js image om te bouwen
FROM node:18 AS build

# Zet de werkdirectory naar /app
WORKDIR /app

# Kopieer de package.json en installeer dependencies
COPY package*.json ./
RUN npm install

# Kopieer de rest van de bestanden en bouw de applicatie
COPY . .
RUN npm run build

# Gebruik een Nginx image om de gecompileerde bestanden te serveren
FROM nginx:alpine

# Kopieer de build van de vorige stage naar de Nginx container
COPY --from=build /app/dist /usr/share/nginx/html

# Exposeer poort 80
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]
