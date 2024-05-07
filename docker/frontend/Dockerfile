FROM node:16 AS build_stage
WORKDIR /app
COPY package*.json .
RUN npm install
COPY . .
RUN npm run build --prod

FROM nginx:alpine
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build_stage /app/dist/frontend /usr/share/nginx/html
EXPOSE ${PORT}