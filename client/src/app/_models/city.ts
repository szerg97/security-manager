import { Gsec } from "./gsec";

export interface City extends Gsec{
    postalCode: string;
    name: string;
    state: string;
    countryId: string;
}