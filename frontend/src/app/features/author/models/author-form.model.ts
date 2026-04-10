export interface AuthorForm {
  firstName: string;
  lastName: string;
  description: string;
  pictureUrl?: string;
  aliases?: string[];
  genres?: string[];
  birthDate?: string;
  deathDate?: string;
}
