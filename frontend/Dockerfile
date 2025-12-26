# ---- Build Stage ----
FROM node:18-alpine AS build

WORKDIR /app

COPY package*.json ./

RUN npm install --legacy-peer-deps


COPY . .

RUN npm run build

# ---- NGINX Stage ----
FROM nginx:alpine

# Remove default nginx page


# Copy dist/build output to nginx
COPY --from=build /app/build /usr/share/nginx/html


EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
