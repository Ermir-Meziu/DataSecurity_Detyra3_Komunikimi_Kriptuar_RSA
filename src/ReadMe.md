# 🔐 Java Encrypted Chat - RSA Project

Ky projekt është një aplikacion **klient-server** i ndërtuar në Java, i cili lejon përdoruesit të komunikojnë në mënyrë të sigurt duke përdorur enkriptimin **RSA (asymmetric encryption)**. Të gjitha mesazhet që dërgohen midis klientëve janë të enkriptuara me çelësin publik të marrësit dhe mund të dekriptohen vetëm nga çelësi privat i tij.

---

## 📦 Paketimi

Të gjitha klasat ndodhen brenda paketës:

package Projekti;


---

## 📁 Skedarët kryesorë

| Skedari             | Përshkrimi                                                                 |
|---------------------|------------------------------------------------------------------------------|
| `RSAEncryption.java`| Krijon çiftin e çelësave (KeyPair) RSA dhe përmban metodat për enkriptim/dekriptim. |
| `ChatServer.java`   | Implementon serverin TCP që menaxhon lidhjet e klientëve dhe shpërndan çelësat publikë. |
| `ChatClient.java`   | Klienti që lidhet me serverin, komunikon me përdorues të tjerë dhe enkripton mesazhet. |

---

## ⚙️ Teknologjitë e përdorura

- Java SE 8 ose më i ri
- RSA Encryption (`javax.crypto`, `java.security`)
- Socket Programming (`java.net`)
- Object Streams (`ObjectInputStream`, `ObjectOutputStream`)
- Multithreading

---

## 🚀 Si të ekzekutoni projektin

### 1. Startoni serverin

Ekzekutoni klasën `ChatServer.java`:

```bash
Run ChatServer.java. 
 ```
Serveri do të nisë në portin 1234 dhe do të presë klientë që të lidhen.

### 2. Nisni klientin

Në një tjetër terminal ose kompjuter:

```bash
java Projekti.ChatClient
```

- Futni emrin e përdoruesit.
- Serveri do t’ju dërgojë listën e përdoruesve të disponueshëm.
- Zgjidhni një përdorues për të dërguar mesazh.
- Futni mesazhin dhe ai do të enkriptohet dhe dërgohet.


## 🧠 Rrjedha e komunikimit
- Klienti gjeneron një çift çelësash RSA (publik dhe privat) lokalisht.

- Çelësi publik dërgohet te serveri bashkë me emrin e përdoruesit.

- Serveri ruan çelësat publikë dhe ua shpërndan klientëve të tjerë.

- Klienti zgjedh një marrës, enkripton mesazhin me çelësin publik të tij dhe e dërgon përmes serverit.

- Marrësi e dekripton mesazhin duke përdorur çelësin e tij privat.

## Detaje teknike

- **Kriptimi:** RSA me çelës 2048-bit
- **Protokolli i komunikimit:** Socket TCP
- **Serializimi:** Object streams (`ObjectInputStream` dhe `ObjectOutputStream`)
- **Struktura e mesazheve:** Mesazhet dërgohen si vargje string me dy elementë: `[marrësi, mesazhi_enkriptuar]`
- **Marrja e çelësave publikë:** Serveri shpërndan një `HashMap<String, PublicKey>` me përdoruesit dhe çelësat e tyre

## Mundësi për zhvillim të mëtejshëm

- Implementimi i autentifikimit të përdoruesve.
- Ruajtja e historikut të mesazheve.
- Menaxhimi i përdoruesve offline dhe mesazheve në pritje.
- Mbështetje për mesazhe grupore.
- Përmirësimi i GUI-së për një ndërfaqe më të përdorshme.