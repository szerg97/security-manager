import { Gsec } from "./gsec";

export interface Transaction extends Gsec{
    currency: string;
    exchangeRate: string;
    faceValue: string;
    denomination: string;
    grossValue: string;
    netValue: string;
    term: string;
    interest: string;
    accruedInterest: string;
    fixedInterest: boolean;
    yield: string;
    referenceYield: string;
    porfolioId: string;
    issuerId: string;
    categoryId: string;
}