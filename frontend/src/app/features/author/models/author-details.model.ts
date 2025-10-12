export interface AuthorDetails {
  id: string;
  firstName: string;
  lastName: string;
  description: string;
  pictureUrl: string;
  aliases: string[];
  genres: string[];
  birthDate: Date;
  deathDate: Date;
  lastEditedTime: Date;
  lastEditedBy: string;
}
