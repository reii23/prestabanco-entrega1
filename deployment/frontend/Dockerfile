FROM node:18 as build
WORKDIR /app
COPY ../front/interface/package*.json ./
COPY ../front/interface/ .
RUN npm install
RUN npm run build

FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
COPY deployment/frontend/nginx.conf /etc/nginx/conf.d/default.conf
RUN chmod -R 755 /usr/share/nginx/html
EXPOSE 3000
CMD ["nginx", "-g", "daemon off;"]
