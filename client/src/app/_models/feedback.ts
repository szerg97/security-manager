import { Gsec } from "./gsec";

export interface Feedback extends Gsec{
    content: string;
    rate: string;
    sent: Date;
    customerId: string;
}