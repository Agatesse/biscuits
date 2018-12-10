import {User} from '../../account/model/User';

export class Kid {
  id: number;
  nickname: string;
  imageUrl: string;
  biscuitsEarned: number;
  user: User;

  constructor(nickname: string, userId: number) {
    this.nickname = nickname;
    this.user = new User(userId, null, null, null, null);
  }
}
