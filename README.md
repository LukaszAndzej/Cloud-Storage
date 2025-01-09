# Cloud Storage System

Cloud Storage System to nowoczesna aplikacja chmurowa, umożliwiająca użytkownikom zarządzanie plikami, obsługę płatności, tworzenie zgłoszeń wsparcia oraz korzystanie z dedykowanego panelu administracyjnego. Projekt oparty jest na architekturze mikroserwisowej, co zapewnia skalowalność, modularność oraz łatwość utrzymania.

## Funkcjonalności

### Główne cechy systemu:
- **Zarządzanie plikami**: Przesyłanie, pobieranie i organizacja plików z możliwością tworzenia katalogów.
- **Obsługa płatności**: Umożliwia zarządzanie subskrypcjami i przetwarzanie transakcji.
- **Wsparcie techniczne**: Tworzenie zgłoszeń oraz śledzenie ich statusu.
- **Panel administracyjny**: Narzędzia do zarządzania użytkownikami i systemem dla administratorów.
- **Frontend**: Intuicyjny interfejs użytkownika z możliwością logowania i rejestracji.

System działa w oparciu o Kubernetes i Helm, co pozwala na szybkie wdrażanie oraz zarządzanie infrastrukturą w środowiskach chmurowych i lokalnych. W ramach projektu zastosowano **Podmana** do emulacji Dockera.

## Wymagania systemowe

Aby uruchomić system, wymagane są:
- **System operacyjny**: Linux (np. Ubuntu 20.04 lub nowszy).
- **Podman**: Wersja 3.0 lub wyższa.
- **Kubernetes**: Wersja 1.20 lub wyższa.
- **Helm**: Wersja 3.0 lub wyższa.
- **Java**: Wersja 17 lub wyższa (Spring Boot).

## Instalacja i uruchamianie

### Krok 1: Klonowanie repozytorium

```bash
git clone https://github.com/LukaszAndzej/Cloud-Storage.git
cd Cloud-Storage
```

### Krok 2: Instalacja zależności

Upewnij się, że Podman i Kubernetes są zainstalowane i działają. Następnie użyj Helma do wdrożenia aplikacji:

```bash
helm upgrade --install cloud-storage ./helm -n default --create-namespace
```

### Krok 3: Sprawdzenie wdrożenia

Po zakończeniu wdrożenia upewnij się, że wszystkie usługi działają:

```bash
kubectl get pods -n default
kubectl get services -n default
```

### Krok 4: Dostęp do frontendu

Po wdrożeniu frontend aplikacji będzie dostępny pod adresem:

```
http://localhost:8080
```

Możesz zalogować się przy użyciu istniejącego konta lub zarejestrować nowe. Interfejs użytkownika umożliwia dostęp do funkcji przesyłania plików, zarządzania subskrypcjami, przeglądania zgłoszeń wsparcia i innych funkcjonalności.

## Struktura systemu

System składa się z następujących komponentów:

- **Auth-Service**: Obsługuje uwierzytelnianie i zarządzanie sesjami.
- **File-Service**: Zarządza operacjami na plikach użytkowników.
- **Payment-Service**: Obsługuje płatności oraz subskrypcje.
- **Support-Service**: Umożliwia zgłaszanie i zarządzanie problemami użytkowników.
- **Frontend**: Zapewnia użytkownikowi dostęp do funkcjonalności poprzez przeglądarkę internetową.
- **Admin-Panel**: Narzędzie dla administratorów do zarządzania użytkownikami i systemem.

## Monitorowanie i debugowanie

System wykorzystuje Prometheus i Grafana do monitorowania wydajności oraz stanu usług. W razie potrzeby można sprawdzić logi poszczególnych komponentów za pomocą:

```bash
kubectl logs <pod-name> -n default
```

## Dane logowania testowego

Dla celów testowych można zalogować się na konto administratora używając następujących danych:
- **Login**: `admin`
- **Hasło**: `admin123`

Możliwe jest również rejestrowanie nowych kont użytkowników za pośrednictwem strony głównej.

## Licencja

Projekt jest objęty licencją MIT. Więcej szczegółów można znaleźć w pliku `LICENSE` w repozytorium.

## Kontakt

W razie pytań lub sugestii prosimy o kontakt pod adresem e-mail: `support@cloudstorage.com` lub poprzez system zgłoszeń dostępny w aplikacji.

