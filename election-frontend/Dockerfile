# Gebruik een Node.js versie 18 (of hoger)
FROM node:18 AS build

# Stel werkmap in
WORKDIR /app

# Kopieer package.json en package-lock.json en installeer afhankelijkheden
COPY package.json package-lock.json ./
RUN npm install

# Kopieer de rest van de applicatie en bouw de productieversie
COPY . .
RUN npm run build

# Gebruik een server zoals Nginx om de gebuilde bestanden te serveren
FROM nginx:alpine

# Kopieer de gebuilde bestanden naar de Nginx-webserver
COPY --from=build /app/dist /usr/share/nginx/html

# Exposeer poort 80
EXPOSE 80

# Start Nginx server
CMD ["nginx", "-g", "daemon off;"]
