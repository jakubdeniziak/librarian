export interface AuthorDetails {
  id: string;
  firstName: string;
  lastName: string;
  description: string;
  pictureUrl?: string;
  aliases?: string[];
  genres?: string[];
  birthDate?: string;
  deathDate?: string;
  lastEditedTime?: string;
  lastEditedBy?: string;
}
