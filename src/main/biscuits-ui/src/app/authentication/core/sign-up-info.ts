export class SignUpInfo {
  private username: string;
  private email: string;
  private password: string;
  private confirmPassword: string;
  private role: string;

  constructor(username: string, email: string, password: string, confirmPassword: string) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.confirmPassword = confirmPassword;
    this.role = 'user';
  }
}
