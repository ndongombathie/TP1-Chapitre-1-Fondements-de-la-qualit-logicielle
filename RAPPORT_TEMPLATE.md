# Rapport qualité - TP1 SunuSanté

Nom / Groupe : Ndongo MBATH , Khady KA / Groupe 6

## 1. Qualité interne vs externe

Avant toute modification, `mvn test` était vert. Cela garantit-il une bonne
qualité interne ? Expliquez en une ou deux phrases, en citant les indices
concrets de la version de départ qui montrent un problème de qualité
interne malgré des tests verts.

# réponse :
meme si tous les tests unitaires sont verts cela ne garantie pas la qualité interne car les tests ne testent que les fonctionnalités et non les conditions d'utilisation.
la complexité cyclomatique des methodes ajouterRendezVous et calculerTotalFacture est élevé ce qui peut être un problème de qualité interne.

## 2. Complexité cyclomatique (avant / après)

| Méthode | Complexité avant | Niveau de risque avant | Complexité après | Niveau de risque après |
|---|---|---|---|---|
| `ajouterRendezVous` | 17 | Modérée, à surveiller | 1 | Simple, faible risque |
| `calculerTotalFacture` | 15 | Modérée, à surveiller | 3 | Simple, faible risque |

## 3. Dette technique identifiée (matrice de Fowler)

Pour chacun des deux problèmes ci-dessous, classez-le dans la matrice
délibérée/involontaire × prudente/imprudente et justifiez en une phrase.

| Problème | Délibérée ou involontaire ? | Prudente ou imprudente ? | Justification |
|---|---|---|---|
| Duplication de la logique de tarif | Délibérée | imprudente | La logique de calcul est répétée au lieu d'être factorisée, ce qui augmente le risque d'incohérences et rend les modifications plus difficiles. |
| Absence de séparation affichage / logique métier | Délibérée | imprudente | Mélanger la logique métier et l'affichage simplifie éventuellement le développement initial, mais rend le code plus difficile à tester, maintenir et faire évoluer. |

## 4. Cycle TDD

- Lien ou hash du commit RED : https://github.com/ndongombathie/TP1-Chapitre-1-Fondements-de-la-qualit-logicielle/commit/f32412e12e37608985095ec1289c73d1d6124286

- Lien ou hash du commit GREEN :https://github.com/ndongombathie/TP1-Chapitre-1-Fondements-de-la-qualit-logicielle/commit/2a1726daa71b65b3828218f59324ab524024d10e

- Lien ou hash du commit REFACTOR : https://github.com/ndongombathie/TP1-Chapitre-1-Fondements-de-la-qualit-logicielle/commit/6c8ffd280c5061718b839295e09e1347d0a3a60f

- Difficulté rencontrée pendant le cycle (le cas échéant) :

## 5. Couverture de code

- Couverture globale obtenue : 97 %
- Classe la moins couverte : GestionRendezVous, pourquoi (justifiée ou pas) ?
- Parce que La couverture des branches (72 %) montre surtout que certains cas conditionnels ne sont pas encore testés.

## 6. Positionnement ISO/IEC 25010

Pour au moins 3 des 8 caractéristiques, indiquez en une phrase si le
refactoring les a améliorées, et comment.

| Caractéristique | Impact du refactoring |
|---|---|
| Maintenabilité | le refactoring a amélioré la maintenabilité car les classes ont plus de complexité cyclomatique et moins de dupliquer de code. |
| Fiabilité | le refactoring a amélioré la fiabilité car les classes ont plus de complexité cyclomatique et moins de dupliquer de code. |
| (votre choix) | |

## 7. Ce que je referais différemment

Deux ou trois phrases sur ce que vous changeriez si c'était à refaire.
- Je referais la classe GestionRendezVous car elle ne respecte pas les principes SOLID.
