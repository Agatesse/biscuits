export class User {
  id: number;
  username: string;
  email: string;
  private password: string;
  private role: string;

  constructor(id: number, username: string, email: string, password: string, role: string) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.role = role;
  }

}
