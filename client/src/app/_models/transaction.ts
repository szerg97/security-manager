export interface Transaction{
    id?: string;
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
    purchased: Date;
    porfolioId: string;
    issuerId: string;
    categoryId: string;
}